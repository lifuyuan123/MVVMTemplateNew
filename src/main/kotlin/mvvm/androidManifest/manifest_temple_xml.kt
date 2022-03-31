package mvvm.androidManifest

fun manifestTemplateXml(packageName:String,activityPackageName:String,activityClass:String) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
	package="$packageName">

    <application>
	    <activity android:name="${activityPackageName}.${activityClass}"
            android:configChanges="orientation|screenSize"
            android:label=""
            android:screenOrientation="portrait"
            android:exported="false"
            android:windowSoftInputMode="stateAlwaysHidden"/>
    </application>
</manifest>
        """