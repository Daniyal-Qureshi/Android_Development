package com.example.midlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class words extends AppCompatActivity {

    String stories[];
    TextView leftwords,placingwords;
    EditText editText;
    ArrayList<String> matches;
    String story;
    int counter,index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        leftwords=(TextView)findViewById(R.id.leftwords);
        placingwords=(TextView)findViewById(R.id.word);
        editText=(EditText) findViewById(R.id.editext);


        stories=new String[]{"I wannabe a <job> when I grow up.Just like my dad.Life is <adjective> like that!"
        ,"One of the most <adjective> characters in fiction is named\n" +
                "Tarzan of the <plural-noun> . Tarzan was raised by a/an\n" +
                "<noun> and lives in the <adjective> jungle in the\n" +
                "heart of darkest <place> . He spends most of his time\n" +
                "eating <plural-noun> and swinging from tree to <noun> .\n" +
                "Whenever he gets angry, he beats on his chest and says,\n" +
                " <funny-noise> ! This is his war cry. Tarzan always dresses in\n" +
                "<adjective> shorts made from the skin of a/an <noun>\n" +
                "and his best friend is a/an <adjective> chimpanzee named\n" +
                "Cheetah. He is supposed to be able to speak to elephants and\n" +
                "<plural-noun> . In the movies, Tarzan is played by <person's-name> ."

                ,
                "Our American universities offer students many <adjective>\n" +
                        "courses that will prepare them to become professional <plural-noun> .\n" +
                        "You can get a degree as a Bachelor of <plural-noun> or take a\n" +
                        "regular liberal <plural-noun> course. Or, if you want to become\n" +
                        "a/an <adjective> engineer, you can study <adjective> mathematics\n" +
                        "and differential <plural-noun> . Then, after <number> years, if\n" +
                        "you want to continue your studies you can write a/an <noun> and\n" +
                        "become a Doctor of <plural-noun> . \n" +
                        "\n" +
                        "When you get out into the <adjective> world, if you have a diploma \n" +
                        "from a university, you will be able to get a job easily as a/an <job-title> \n" +
                        "or even a/an <job-title> . If you don't have a diploma, you may have to take\n" +
                        "a job as a <noun> . \n" +
                        "\n" +
                        "Remember, it's important that you study hard in high school so you are able \n" +
                        "to do well on your college entrance <plural-noun> . It is true that \"a little \n" +
                        "learning is a/an <adjective> thing.",
                "<Male-Name> has announced that his <adjective>\n" +
                        "clothing store in the heart of downtown <CITY> is having\n" +
                        "a/an <adjective> sale of all merchandise, including\n" +
                        "<unusual-adjective> suits and slightly irregular <plural-noun>\n" +
                        "available. Men's cable-knit <plural-noun> , only $15.99.\n" +
                        "Hand-woven Italian <plural-noun> , 1/2-price. Double-\n" +
                        "breasted cashmere <plural-noun> , $50.00. Genuine imported\n" +
                        "<Color!> <adjective> shoes, <Exciting-adjective> handerchiefs,\n" +
                        "and women's embroidered <plural-noun> , all at rock-bottom prices.\n" +
                        "This is a chance to get some really <Interesting-Adjective> bargains."

                ,"Here's how you dance the Monstrosity. Stand with your feet together.\n" +
                "Now, move your left foot <aDvErB> to the side. Now stamp your\n" +
                "right foot <NUMBER> times and put your hands on your partner's\n" +
                "<Plural-Noun> . Next, you both <verb> slowly to the right and bend\n" +
                "your <body-part> backward. For the next eight counts,\n" +
                "both of you <verb> <adverb> to the left. Next, you and\n" +
                "your partner stand back to back and wiggle your <pluRAL-nOUN> and\n" +
                "slap your <plural-noun> together. Don't forget to keep stamping\n" +
                "your right foot. Now, face your partner again, put your <plural-noun>\n" +
                "together and shout,  <FUNNY-noise> ! Now, <verb> backward\n" +
                "and repeat the whole thing <Number> times. If you feel that you can't\n" +
                "learn this dance, you can always <verB> the next one out."
        };

        story=stories[new Random().nextInt(5)];
        Pattern pattern = Pattern.compile("\\<(.*?)\\>");
        Matcher matcher = pattern.matcher(story);

        matches = new ArrayList<String>();
        while(matcher.find())
            matches.add(matcher.group());


        counter=matches.size();
        index=0;
        leftwords.setText(counter +"words(s) left");
        String w=matches.get(index);
        w=w.replace("<","");
        w=w.replace(">","");
        placingwords.setText("please type a/an "+w);
        editText.setHint(w);
        Log.i("dic",matches.toString());
    }



    public  void Ok(View view){
        if(TextUtils.isEmpty(editText.getText().toString()))
        {
            editText.setError("Empty field");
            return;
        }


        Toast.makeText(this,"Great keep  going",Toast.LENGTH_LONG).show();
        leftwords.setText(--counter +"words(s) left");
        String w=matches.get(index);
        story=story.replaceFirst(w,editText.getText().toString());
        if(counter==0)
        {
            Intent intent=new Intent(this,story.class);
            intent.putExtra("story",story);
            startActivity(intent);


        }



        Log.i("story",story);
        w=matches.get(++index);
        w=w.replace("<","");
        w=w.replace(">","");
        placingwords.setText("please type a/an "+w);
        editText.setHint(w);
        editText.setText("");

    }
    public void another(View view){

        finish();
    }

}