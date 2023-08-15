package com.example.experimentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MainActivity extends AppCompatActivity {

    private MutableLiveData<Integer> studentId = new MutableLiveData<>();
    private LiveData<Student> studentLiveData;

    private AppCompatButton buttonOne, buttonTwo;
    public boolean isEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        buttonOne = findViewById(R.id.button_one);
        buttonTwo = findViewById(R.id.button_two);


        buttonOne.setOnClickListener(new DebounceClickListener() {
            @Override
            void debouncedClick() {
                Log.d("Farman", "is Empty : " + isEmpty);
                testCallable();
                //  startActivity(new Intent(MainActivity.this,SecondActivity.class));
                // studentId.setValue(1);
             //   Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        buttonTwo.setOnClickListener(new DebounceClickListener() {
            @Override
            void debouncedClick() {
                //  Log.d("Farman", "Button two clicked");
                studentId.setValue(2);
            }
        });
        studentLiveData = Transformations.map(studentId, this::getStudentById);
      /*  studentLiveData.observe(this, new Observer<Student>() {
            @Override
            public void onChanged(Student student) {
                Log.d("Farman","Student Name : "+student.getName());
            }
        });*/


        //   Map courseFees = (Map) Proxy.newProxyInstance(HashMap.class.getClassLoader(),new Class[]{Map.class},new ProxyHashMapHandler(new HashMap<>()));


    }


    private Student getStudentById(int id) {
        SparseArray<Student> studentSparseArray = new SparseArray<>();
        studentSparseArray.put(1, new Student("Farman", 32));
        studentSparseArray.put(2, new Student("Ellena", 2));
        Student student = studentSparseArray.get(id);
        Log.d("Farman", "Selected name : " + student.getName());
        return student;
    }


    private void testCallable(){
        Callable<String> stringCallable = () -> "Hello, Farman!!";
        Callable<String> stringCallable1 = ()-> "Hello, Elleana!!";
        Callable<String> stringCallable2 = ()-> "Hello, Rimi";

        ExecutorService executorService = Executors.newFixedThreadPool(1);
       /* Future<String> stringFuture = executorService.submit(stringCallable);
        try {
            Log.d("Farman","Result From Callable : "+stringFuture.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        ArrayList<Callable<String>> callableArrayList = new ArrayList<>();
        callableArrayList.add(stringCallable);
        callableArrayList.add(stringCallable1);
        callableArrayList.add(stringCallable2);
        try {
            List<Future<String>> futureResult = executorService.invokeAll(callableArrayList);
           // Log.d("Farman", "Result : "+futureResult);
            for(int i=0;i<futureResult.size();i++)
            {
                Log.d("Farman", "Result : "+futureResult.get(i).get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}