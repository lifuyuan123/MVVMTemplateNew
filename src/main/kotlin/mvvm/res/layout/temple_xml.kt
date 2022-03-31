package mvvm.res.layout

fun templateXml(fragmentPackageName:String,name:String="fragment") = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
        <variable
            name="$name"
            type="$fragmentPackageName" />
    </data>

    <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
    
    </LinearLayout>
</layout>
        """