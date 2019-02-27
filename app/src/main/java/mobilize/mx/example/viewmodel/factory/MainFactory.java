package mobilize.mx.example.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import mobilize.mx.core.rest.ProjectRepository;
import mobilize.mx.example.viewmodel.MainViewModel;

public class MainFactory extends ViewModelProvider.NewInstanceFactory {
    private ProjectRepository repository;

    @Inject
    public MainFactory(ProjectRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}