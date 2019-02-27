package mobilize.mx.example.components;

import javax.inject.Singleton;

import dagger.Component;
import mobilize.mx.core.module.RepositoryModule;
import mobilize.mx.example.ui.MainActivity;

@Component(modules = {RepositoryModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
}
