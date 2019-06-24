# Validator for Java

[![Build Status](https://travis-ci.org/yoojia/validator.svg?branch=master)](https://travis-ci.org/yoojia/validator)

`Validator-Java`是一个表单参数校验库，基于我另一个Android项目[NextInputs-Android](https://github.com/yoojia/NextInputs-Android)。
应用于非Spring体系的Web开发中，目前我用于[SparkJava](https://github.com/perwendel/spark)Web框架下的程序开发。
SparkJava是一个轻量级JavaWeb框架，它与Spring不同的是占用资源很少。在物联网线下场景，Spring就是个性能怪兽，SparkJava则是非常合适的选择。
但Spark缺少相应的表单校验库，本项目[Validator](https://github.com/yoojia/validator)是为Spark场景设计，已应用于多个项目。

* 轻量级，纯JDK实现，无任何第三方库依赖；
* 内置常用校验模式;
* 支持自定义校验模式扩展;
* 支持自定义校验目标扩展;

## 配置

当前版本尚未推入JCenter库，需要手动添加Repository依赖：

Gradle:

```groovy
repositories {
	maven {
		url  "https://dl.bintray.com/nextabc/maven"
	}
}
```

Maven:

```xml
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-nextabc-maven</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/nextabc/maven</url>
    </repository>
</repositories>
```

添加依赖：

Gradle:

```groovy
compile 'net.nextabc:validator:1.1.3-Final'
```

Maven:

```xml
<dependency>
  <groupId>net.nextabc</groupId>
  <artifactId>validator</artifactId>
  <version>1.1.3-Final</version>
  <type>pom</type>
</dependency>
```


## 使用

```java
// 创建Validator，指定检查参数及检查模式
final Validator validator = new Validator()
        .addField("fullName", maxLength(20))
        .addField("username", rangeLength(4, 32))
        .intField("blockId", digits(), maxLength(3))
        .intField("buildingId", digits(), maxLength(3))
        .intField("floorId", digits(), maxLength(3))
        .intField("roomId", digits(), maxLength(3))
        .boolField("disabled", inSet(CollectionX.BOOL))
        .boolField("reviewing", inSet(CollectionX.BOOL))
        .boolField("roomOwner", inSet(CollectionX.BOOL))
        .intField("current", require(), digits(), maxLength(4))
        .intField("pageSize", require(), digits(), maxLength(4));

// 从Request中获取参数列表
final Map<String, String> query = Parameters.query(request);

// 检查参数，此处，如果检查失败会抛出ValidationException
validator.check(query);

// 解析参数，获取Key-Value在程序中使用
final MapValue values = MapValue.of(validator.parseByKey(query));

```

## 校验模式列表

### StaticScheme - 静态检查

**静态检查**也可以称为数据格式匹配模式。
它的明显特征是在校验数据时时，不需要依赖基准参数来判断，而是直接对用户输入的数据，检查其格式是否符合要求。
如“邮件地址”、“电话号码”等数据格式的校验就属于此模式。

目前内置包含以下静态检查模式，在未来版本也会加入其它使用频率较高的模式：

- Required - 必填模式
- Digits - 数字模式
- Email - 邮件地址模式
- IPv4 - IP地址模式
- Host - 域名模式
- Letters - 字母模式
- DigitLetters - 数字字母模式
- BankCard - 银行卡/信用卡号码模式
- CNIDCard 身份证号码模式
- CNMobile 手机号码模式（国内）

### ValueScheme - 数值检查模式

**数值检查模式**需要指定校验参数来完成校验。
在这种模式下，需要你提供一个基准值来做判断。例如“最小值”、“密码长度”等方式，就是使用这种模式。

Validator目前内置包含以下几种数值校验模式，在未来版本也会加入其它使用频率较高的模式：

- MinLength - 最小内容长度
- MaxLength - 最多内容长度
- RangeLength - 内容长度在指定范围内
- FixedLength - 内容长度在固定值

### 重点说明 Required - 必填模式

> 设置`Required`模式后，输入内容不能为空，这是Validator不允许空值的两条校验规则之一

在设置Required模式后，Required模式在校验时会优先于其它模式。其它校验模式在默认情况下将按代码添加顺序依次执行。

这里特别说明的是：

- Required的校验优先级默认为 `net.nextabc.validator.Schemes.HIGH_PRIORITY`，其它优先级为 `DEFAULT_PRIORITY`。
- **重要，重要，重要**：如果设置的校验条目中，你没有添加Required校验模式，在输入**内容为空**的情况下，校验结果是通过（Passed）的。

## 空值

NextInputs对输入数据空值字符串的定义是：

```
input == null || input.length() == 0
```

## Author

- 陈哈哈 yoojiachen@gmail.com

## License

The Apache Software License, Version 2.0

See [LICENSE](LICENSE)
