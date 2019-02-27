package mobilize.mx.example;

import android.app.Application;
import android.content.Context;

import mobilize.mx.core.module.RepositoryModule;
import mobilize.mx.example.components.AppComponent;
import mobilize.mx.example.components.DaggerAppComponent;
import timber.log.Timber;

public class App extends Application {
    private static App app;
    private static Context context;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        App.context = getApplicationContext();

        appComponent = DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule(this)).build();


        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static App getApp() {
        return app;
    }

    public static Context getAppContext() {
        return App.context;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}