package mobilize.mx.example.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import mobilize.mx.core.model.Posts;
import mobilize.mx.example.App;
import mobilize.mx.example.R;
import mobilize.mx.example.databinding.ActivityMainBinding;
import mobilize.mx.example.viewmodel.MainViewModel;
import mobilize.mx.example.viewmodel.factory.MainFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    String TAG = this.getClass().getSimpleName();
    private LifecycleRegistry mLifecycleRegistry;
    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Inject
    MainFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getAppComponent().inject(this);
        Timber.tag(TAG);

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        binding.setViewModel(viewModel);

        init();
    }

    private void init() {
        loadPosts();
    }

    private void loadPosts() {
        viewModel.getPosts().observe(this, postsObserver);
    }

    Observer<List<Posts>> postsObserver = new Observer<List<Posts>>() {
        @Override
        public void onChanged(@Nullable List<Posts> posts) {
            Timber.d("Posts onChanged");
            if (posts != null && posts.size() > 0) {
                Timber.d("Post list size: %s", posts.size());
                for (Posts post : posts) {
                    Timber.d("Post title: %s", post.title);
                }
            }
        }
    };

    public void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
