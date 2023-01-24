## Activities, Activity Lifecycle and Activity Data Passing

### To go from one activity to another ?

We use `Intent` to navigate from activity to another. The idea is tell the Android system which activity you would like to go to. Below is the code for using the lancher to start an activity for result.

```java
public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        //use the returned data
                        String name = data.getStringExtra(SecondActivity.NAME_KEY);
                    } else {
                        // Handle the error
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startForResult.launch(intent);
            }
        });
    }
}
```

Below is the code for passing the data back to the launching activity.

```java
public class SecondActivity extends AppCompatActivity {
    public static final String NAME_KEY = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(NAME_KEY, "Bob Smith");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
```
