package mobilize.mx.example.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import mobilize.mx.core.model.Posts;
import mobilize.mx.core.rest.ProjectRepository;
import timber.log.Timber;

public class MainViewModel extends ViewModel {
    String TAG = this.getClass().getSimpleName();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private ProjectRepository projectRepository;

    public MainViewModel(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        Timber.tag(TAG);
    }

    public MutableLiveData<List<Posts>> getPosts() {
        final MutableLiveData<List<Posts>> response = new MutableLiveData<>();
        disposables.add(
                projectRepository.getPosts().subscribeWith(new DisposableSingleObserver<List<Posts>>() {

                    @Override
                    public void onSuccess(List<Posts> posts) {
                        if (posts != null) {
                            Timber.d("Posts List Size: %s", posts.size());
                            response.postValue(posts);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("Error:  %s", e.getCause());
                    }
                }));
        return response;
    }


    @Override
    protected void onCleared() {
        disposables.clear();
    }

}
