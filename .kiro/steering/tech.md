# 技术栈

## 构建系统
- **Gradle**: Android Gradle Plugin 8.9.1
- **Kotlin**: 版本 1.9.20
- **Java 兼容性**: Java 17 (源码和目标版本)

## Android 配置
- **编译 SDK**: 35
- **最低 SDK**: 24
- **目标 SDK**: 35
- **应用程序 ID**: `cn.app.tools.calculator`
- **命名空间**: `cdn.kotlincalculator`

## 核心依赖
- **Kotlin 标准库**: `kotlin-stdlib-jdk7`
- **AndroidX AppCompat**: 1.1.0
- **AndroidX Core KTX**: 1.1.0
- **ConstraintLayout**: 1.1.3
- **exp4j**: 0.4.8 (数学表达式求值)

## Android 特性
- **View Binding**: 已启用，用于类型安全的视图引用
- **Kotlin Parcelize**: 已启用，用于 Parcelable 实现
- **ProGuard**: 发布版本启用代码混淆

## 测试框架
- **JUnit**: 4.12 (单元测试)
- **AndroidX Test**: JUnit 1.1.1, Espresso 3.2.0 (仪器测试)

## 常用命令

### 构建命令
```bash
# 清理构建
./gradlew clean

# 构建调试版 APK
./gradlew assembleDebug

# 构建发布版 APK
./gradlew assembleRelease

# 安装调试版到连接的设备
./gradlew installDebug
```

### 测试命令
```bash
# 运行单元测试
./gradlew test

# 运行仪器测试
./gradlew connectedAndroidTest

# 运行所有测试
./gradlew check
```

### 开发命令
```bash
# 生成签名 APK (需要配置密钥库)
./gradlew assembleRelease

# 检查依赖更新
./gradlew dependencyUpdates
```