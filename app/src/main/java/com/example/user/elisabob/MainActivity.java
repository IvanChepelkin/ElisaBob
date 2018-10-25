package com.example.user.elisabob;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textWin;
    private TextView ballsBob;
    private TextView ballsElisa;
    private List<Person> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        searchWhoIsWin(initPerson());
    }

    private void initView() {
        textWin = findViewById(R.id.texWin);
        ballsBob = findViewById(R.id.ballsBob);
        ballsElisa = findViewById(R.id.ballsElisa);
    }

    private List<Person> initPerson() {
        list = new ArrayList<>();
        list.add(new Person("Bob", "EMHH"));
        list.add(new Person("Elisa", "EMMH"));
        return list;
    }

    @SuppressLint("SetTextI18n")
    private void searchWhoIsWin(List<Person> people) {
        List<Integer> worksList = new ArrayList<>();
        int hours = 0;
        int position = 0;
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            String work = person.getWork();
            char[] strWorkToArray = work.toCharArray();

            for (int j = 0; j < strWorkToArray.length; j++) {
                for (SearchHours s : SearchHours.values()) {
                    if (s.name().equals(String.valueOf(strWorkToArray[j]))) {
                        hours = s.action(hours);
                    }
                }
            }
            worksList.add(hours);
            hours = 0;
            if (person.getName().equals("Bob")) {
                ballsBob.setText(worksList.get(i).toString());
            } else if (person.getName().equals("Elisa")) {
                ballsElisa.setText(worksList.get(i).toString());
            }
        }
        int max = worksList.get(0);
        for (int i = 0; i <= worksList.size() - 1; i++) {
            if (worksList.get(i) > max) {
                max = worksList.get(i);
                position = i;
            }
        }
        textWin.setText("Победу одержал " + people.get(position).getName());
    }

    enum SearchHours {
        E() {
            public int action(int x) {
                return x + 1;
            }
        },
        M {
            public int action(int x) {
                return x + 3;
            }
        },
        H {
            public int action(int x) {
                return x + 5;
            }
        };
        public abstract int action(int x);
    }
}