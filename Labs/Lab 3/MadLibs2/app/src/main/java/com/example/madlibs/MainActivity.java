package com.example.madlibs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    File f;
    SharedPreferences perfs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void start(View view){
        Intent intent=new Intent(this,screen.class);
            perfs=getSharedPreferences("Stories",MODE_PRIVATE);


            if(!(perfs.contains("story")))
            {
                SharedPreferences.Editor ed=perfs.edit();
                Set<String> set = new HashSet<String>();
                set.add("I wannabe a <job> when I grow up. Just like my dad. Life is <adjective> like that!");
                set.add("One of the most <adjective> characters in fiction is named \"Tarzan of the <plural-noun> .\" Tarzan was raised by a/an <noun> and lives in the <adjective> jungle in the heart of darkest <place> . He spends most of his time eating <plural-noun> and swinging from tree to <noun>. Whenever he gets angry, he beats on his chest and says, \"<funny-noise>!\" This is his war cry. Tarzan always dresses in <adjective> shorts made from the skin of a/an <noun> and his best friend is a/an <adjective> chimpanzee named Cheetah. He is supposed to be able to speak to elephants and <plural-noun> . In the movies, Tarzan is played by <person's-name>.");
                set.add("Our American universities offer students many <adjective> courses that will prepare them to become professional <plural-noun>. You can get a degree as a Bachelor of <plural-noun> or take a regular liberal <plural-noun> course. Or, if you want to become a/an <adjective> engineer, you can study <adjective> mathematics and differential <plural-noun> . Then, after <number> years, if you want to continue your studies you can write a/an <noun> and become a Doctor of <plural-noun>. When you get out into the <adjective> world, if you have a diploma from a university, you will be able to get a job easily as a/an <job-title> or even a/an <job-title> . If you don't have a diploma, you may have to take a job as a <noun>. Remember, it's important that you study hard in high school so you are able to do well on your college entrance <plural-noun> . It is true that \"a little learning is a/an <adjective> thing.\"");
                set.add("<Male-Name> has announced that his <adjective> clothing store in the heart of downtown <CITY> is having a/an <adjective> sale of all merchandise, including <unusual-adjective> suits and slightly irregular <plural-noun> available. Men's cable-knit <plural-noun> , only $15.99. Hand-woven Italian <plural-noun> , 1/2-price. Double-breasted cashmere <plural-noun> , $50.00. Genuine imported <Color!> <adjective> shoes, <Exciting-adjective> handerchiefs, and women's embroidered <plural-noun> , all at rock-bottom prices. This is a chance to get some really <Interesting-Adjective> bargains.");
                set.add("Here's how you dance the Monstrosity. Stand with your feet together. Now, move your left foot <aDvErB> to the side. Now stamp your right foot <NUMBER> times and put your hands on your partner's <Plural-Noun>. Next, you both <verb> slowly to the right and bend your <body-part> backward. For the next eight counts, both of you <verb> <adverb> to the left. Next, you and your partner stand back to back and wiggle your <pluRAL-nOUN> and slap your <plural-noun> together. Don't forget to keep stamping your right foot. Now, face your partner again, put your <plural-noun> together and shout, \" <FUNNY-noise> !\" Now, <verb> backward and repeat the whole thing <Number> times. If you feel that you can't learn this dance, you can always <verB> the next one out.");
                ed.putStringSet("story",set);
                ed.commit();
//                Log.d("Inside","Inside for first time");
            }



        startActivity(intent);
    }
}