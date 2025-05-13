💬 JavaFX Chat Application
A simple and modern chat UI built with JavaFX.

📌 Description
This is a lightweight JavaFX-based chat interface with a modern dark theme. Users can type and send messages, and receive automatic responses (AI logic can be extended in AIService.java).

🧱 Features
Dark mode design.

Interactive chat interface.

Message sending via button or Enter key.

Clean and extendable code structure.

🚀 How to Run
In IDE (IntelliJ IDEA or similar):
Open the project as a Java project.
Run ChatUIFX.java.
Make sure you have Java 17+ and JavaFX properly configured.

📁 Project Structure
bash
Copy
Edit
src/
 └── org.example/
     ├── ChatUIFX.java        # Main UI
     ├── AIService.java       # (Optional) AI response logic
     └── SettingsPage.java    # Settings page
📤 Publishing to GitHub

Developed by moamenalmahe



---------------------------------------------------------------------------------------------------------------------------------------

# JavaFX Chat App 💬

تطبيق محادثة بسيط وحديث باستخدام JavaFX.

## 📌 الوصف

هذا التطبيق يعرض واجهة محادثة مصممة باستخدام JavaFX مع دعم لإرسال الرسائل عبر واجهة رسومية. التطبيق يتيح للمستخدم كتابة رسالة والحصول على رد تلقائي باستخدام نموذج ذكي (يمكن ربطه بـ OpenAI أو أي منطق رد).

> تم تصميم الواجهة لتكون بسيطة وجذابة، مع دعم الألوان الداكنة وتأثيرات عصرية.

## 🧱 مميزات

- تصميم داكن جميل باستخدام JavaFX.
- واجهة قابلة للتوسعة.
- رسائل تظهر على اليمين (للمستخدم) واليسار (للنظام).
- تحكم في الرسائل النصية (زر إرسال أو Enter).

## 🚀 التشغيل

### باستخدام IntelliJ IDEA أو Eclipse:

1. افتح المشروع كـ JavaFX Project.
2. شغل الملف `ChatUIFX.java` كـ `Java Application`.

### عبر الطرفية (Terminal):

```bash
javac -d out src/org/example/*.java
java -cp out org.example.ChatUIFX
src/
 └── org.example/
     ├── ChatUIFX.java        # الواجهة الرئيسية
     ├── AIService.java       # (اختياري) منطق الذكاء الصناعي
     └── SettingsPage.java    # صفحة الإعدادات
تم تطوير المشروع بواسطة moamenalmahe
