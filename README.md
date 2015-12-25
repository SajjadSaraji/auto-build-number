# auto-build-number
Generate Auto Build Number for your Android Application And Append to your Version String

How to use!
1. Create a file in your application root directory with name : version.properties

2. Add VERSION_BUILD = 0 to file and save it ( you can add your costume number if you build it before)

3. Now You just Code some groovy scripts:


3.1. open gradle.build application file and append codes : 



    def versionPropsFile = file('version.properties')

    if (versionPropsFile.canRead()) {
        def Properties versionProps = new Properties()
        versionProps.load(new FileInputStream(versionPropsFile))
        def versionBuild = versionProps['VERSION_BUILD'].toInteger() + 1
        versionProps['VERSION_BUILD'] = versionBuild.toString()
        versionProps.store(versionPropsFile.newWriter(), null)

        defaultConfig {
        applicationId "ir.geekart.firstrun"
        minSdkVersion 14
        targetSdkVersion 23
        versionCode 1
        versionName "1.0." + versionBuild
    } }
        else {
            throw new GradleException("Could not read version.properties!")
        }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

NOTE**** Open Brucket with defaultConfig { } is depend on your Project, delete it,


4. Re-Sync Application Gradle files

5. with each build application, auto-increament script increases your build number

6. you can parse your build number on application ( codes in MainActivity )

7. Star My Final Commits
