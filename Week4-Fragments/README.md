## Fragment Lifecycle and Data Passing Between Fragments

### Setup View Binding in Project
View binding will make it much easier to manage the UI components in your code. It creates a classes that represent each of the layout xml files, which enable you to have access to all the layout components that have id's assigned to them. To setup follow the following steps:
1. Open the Module (app) build.gradle file
2. Under the `android` section include the following
```yaml
buildFeatures {
    viewBinding true
}
```

Using View Binding in an Activity:
```java
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
```

Using View binding in Fragment:
```java
public class MainFragment extends Fragment {
    public MainFragment() {
        // Required empty public constructor
    }

    FragmentMainBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_main, container, false);
        
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
```
