# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/quangnam/android-sdks/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in buildStream.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Project source
-keep class * extends Throwable

# for android
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
    public void set*(***);
    public *** get*();
    <fields>;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keep public class * extends android.view.View {
      public <init>(android.content.Context);
      public <init>(android.content.Context, android.util.AttributeSet);
      public <init>(android.content.Context, android.util.AttributeSet, int);
      void set*(...);
      *** get*();
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.content.Context {
    public void *(android.view.View);
    public void *(android.view.MenuItem);
}

## --------------- For native methods ---------------------
-keepclasseswithmembernames class * {
    native <methods>;
}

# android support
-keep class android.support.** { *;}
-keep class android.support.v4.** { *;}
-keep interface android.support.v4.** { *;}
-keep class android.support.v7.** { *;}
-keep interface android.support.v7.** { *;}

# android design
-keep class android.support.design.** { *;}
-keep interface android.support.design.** { *;}
-keep public class android.support.design.R$* { *;}

# ConstraintLayout
-keep class android.support.constraint.** { *;}
-keep interface android.support.constraint.** { *;}
-keep public class android.support.constraint.R$* { *;}

#BuildConfig
-keep class *.BuildConfig { *;}

#debugable
-keepattributes SourceFile,LineNumberTable

# Dagger
-dontwarn com.google.errorprone.annotations.*

# Retrofit
-dontwarn okio.**
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions