package com.tao.demo.utils.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Maps;
import com.tao.demo.enums.TableTypeEnum;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Types;
import java.util.Collection;
import java.util.Map;


/**
 * @author DemoTao
 */
@Log4j2
@Data
public class MybatisPlusCodeGeneratorUtil {
  /**
   * 数据库的url
   */
  private static final String URL = "jdbc:mysql://mysql.niqijian.cn:63852/cine_ticket_hub_db?serverTimezone=GMT%2B8&useUnicode=true&useSSL=false&characterEncoding=utf8&rewriteBatchedStatements=true";
  /**
   * 数据库的账号
   */
  private static final String USERNAME = "cine_ticket_hub";
  /**
   * 数据库的密码
   */
  private static final String PASSWORD = "cine_ticket_hub_123";
  /**
   * 包名
   */
  private static final String MAVEN_PATH = "\\src\\main\\java";
  private static final String PACKAGE_NAME = "com.tao.demo";
  private static final String PACKAGE_PATH = "\\com\\tao\\demo";
  
  private static final String MAPPER_XML_PATH = "\\src\\main\\resources\\mapper";
  private static final String MAPPER_PATH = "\\mapper";
  private static final String SERVICE_PATH = "\\service";
  private static final String SERVICE_IMPL_PATH = "\\service\\impl";
  private static final String CONTROLLER_PATH = "\\controller";
  private static final String ENTITY_PATH = "\\domain\\entity";
  private static final String WEB_MODEL_NAME = "\\demo-boot-web";
  
  public static void main(String[] args) {
    TableTypeEnum tableTypeEnum = TableTypeEnum.FILM_TABLES;
    Collection<String> tables = tableTypeEnum.getTables();
    log.info("开始生成{}对应数据表代码", tableTypeEnum.getName());
    log.info("生成表：{}", tables);
    MybatisPlusCodeGeneratorUtil.generatorTable(tableTypeEnum.getModelName(), tableTypeEnum.getPrefix(), tables);
    log.info("生成{}对应数据表代码完成", tableTypeEnum.getName());
  }
  
  /**
   * 需要生成的表名
   *
   * @param tables 表名集合
   */
  public static void generatorTable(String moduleName, String prefix, Collection<String> tables) {
    generatorTable(moduleName, prefix, tables.toArray(new String[0]));
  }
  
  /**
   * 需要生成的表名
   *
   * @param tableTypeEnum 模块枚举对应表名
   */
  public static void generatorTable(String moduleName,String prefix, TableTypeEnum tableTypeEnum) {
    generatorTable(moduleName, prefix, tableTypeEnum.getTables());
  }
  
  /**
   * 需要生成的表名
   *
   * @param tables 表名数组
   */
  public static void generatorTable(String moduleName, String prefix, String... tables) {
    
    String projectPath = System.getProperty("user.dir");
    String modulePath = projectPath + "\\" + moduleName;
    String srcPath = modulePath + MAVEN_PATH;
    log.info("模块代码生成位置:{}", modulePath);
    // 配置
    Map<OutputFile, String> pathInfo = Maps.newHashMap();
    pathInfo.put(OutputFile.xml, modulePath + MAPPER_XML_PATH);
    pathInfo.put(OutputFile.controller, projectPath + WEB_MODEL_NAME + MAVEN_PATH + PACKAGE_PATH  + CONTROLLER_PATH +"/"+ prefix);
    
    //        数据库的配置信息
    FastAutoGenerator.create(URL, USERNAME, PASSWORD)
      .globalConfig(builder -> {
        builder.author("LiTao") // 设置作者
          .disableOpenDir() // 禁止打开输出目录
          .outputDir(srcPath); // 指定输出目录
      })
      .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
        int typeCode = metaInfo.getJdbcType().TYPE_CODE;
        if (typeCode == Types.SMALLINT) {
          // 自定义类型转换
          return DbColumnType.INTEGER;
        }
        return typeRegistry.getColumnType(metaInfo);
      }))
      .packageConfig(builder -> {
        builder.parent(PACKAGE_NAME) // 设置父包名
          .entity("domain.entity") // 设置实体包名
          .pathInfo(pathInfo); // 设置mapperXml生成路径
      })
      
      .strategyConfig(builder ->
          builder.addInclude(tables) // 设置需要生成的表名
            .addTableSuffix("_tb") // 过滤表后缀
            .addTablePrefix(prefix +"_") // 过滤表前缀
            // Entity 策略配置
            .entityBuilder()
            .superClass("com.tao.demo.core.domain.BaseEntity") // 设置父类
            .addSuperEntityColumns("id", "deleted", "update_id", "update_time", "create_id", "create_time") // 设置父类公共字段
            .enableLombok() // 开启 lombok 模型
            .enableChainModel() //开启链式模型
            .enableColumnConstant() //开启字段常量
            .enableTableFieldAnnotation() //开启字段注解
            // .enableActiveRecord() //开启 activeRecord 模式
//          .enableFileOverride() // 覆盖已生成文件
            // Mapper 策略配置
            .mapperBuilder()
            .superClass("com.github.yulichang.base.MPJBaseMapper")
            .mapperAnnotation(Mapper.class) // 设置mapper接口上面的注解类
            .enableFileOverride() // 覆盖已生成文件
            // Controller 策略配置
            .controllerBuilder()
            .superClass("com.tao.demo.core.controller.BaseController") // 设置父类
            .enableHyphenStyle() // 开启驼峰转连字符
            .enableRestStyle() // 开启生成@RestController控制器
            // Service 策略配置
            .serviceBuilder()
            .formatServiceFileName("%sService")//生成的类结果名
            .superServiceClass("com.github.yulichang.extension.mapping.base.MPJDeepService")
            .formatServiceImplFileName("%sServiceImpl")
            .superServiceImplClass("com.github.yulichang.base.MPJBaseServiceImpl")
//          .enableFileOverride() // 覆盖已生成文件
      )
      .templateEngine(new FreemarkerTemplateEngine())
      .execute();
  }
  
}
