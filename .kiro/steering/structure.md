# 项目结构

## 代码组织

### 包结构
- **主包**: `cdn.kotlincalculator`
- **应用程序 ID**: `cn.app.tools.calculator`

### 源码目录
```
app/src/main/java/cdn/kotlincalculator/
├── MainActivity.kt          # 主活动，包含计算器逻辑
└── Api35Utils.kt           # Android API 35 兼容性工具类
```

### 资源目录
```
app/src/main/res/
├── layout/
│   └── activity_main.xml   # 主界面布局
├── values/
│   ├── colors.xml          # 颜色定义
│   ├── strings.xml         # 字符串资源
│   └── styles.xml          # 样式定义
└── drawable/               # 图标和图片资源
```

## 编码规范

### Kotlin 代码风格
- 使用 camelCase 命名变量和函数
- 使用 PascalCase 命名类和对象
- 优先使用 `val` 而不是 `var`
- 使用 View Binding 访问视图组件
- 遵循 Android 官方 Kotlin 风格指南

### 命名约定
- **Activity**: `MainActivity`, `SettingsActivity`
- **View ID**: `num0`, `actionAdd`, `placeholder`
- **样式**: `NumberButtonStyle`, `ActionButtonStyle`
- **资源**: 使用下划线分隔，如 `activity_main`

### 文件组织
- 每个 Activity 对应一个 Kotlin 文件
- 工具类使用 `Utils` 后缀
- 布局文件使用 `activity_` 或 `fragment_` 前缀

## UI 设计模式

### 布局结构
- 使用 LinearLayout 作为主容器
- 按钮使用 TextView 实现，应用自定义样式
- 采用权重布局实现响应式设计

### 样式系统
- `NumberButtonStyle`: 数字按钮样式
- `ActionButtonStyle`: 操作按钮样式
- 统一的颜色主题（黑色背景，白色文字）

### View Binding 使用
```kotlin
private lateinit var binding: ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    
    // 使用 binding 访问视图
    binding.num0.setOnClickListener { ... }
}
```

## 依赖管理

### 第三方库
- **exp4j**: 数学表达式求值引擎
- **AndroidX**: 现代 Android 支持库

### 版本管理
- 在项目级 `build.gradle` 中定义 Kotlin 版本
- 使用统一的依赖版本管理
- 定期更新依赖到稳定版本