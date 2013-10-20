package com.example.flagquizgame;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.*;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

public class FlagQizGame extends Activity {

	 private static final String TAG ="FlagQuizGame Activity";
	 private List<String> fileNameList;
	 private List<String> quizCountriesList;
	 private Map<String,Boolean> regionsMap;
	 private String correctAnswer;
	 private int totalGuesses;
	 private int correctAnswers;
	 private int guessRows;
	 private Random random;
	 private Handler handler;
	 private Animation shakeAnimation;
	 
	 private TextView answerTextView;
	 private TextView questionNumberTextView;
	 private ImageView flagImageView;
	 private TableLayout buttonTableLayout;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flag_quiz_game);
		fileNameList = new ArrayList<String>();
		quizCountriesList= new ArrayList<String>();
		regionsMap = new HashMap<String,Boolean>();
		guessRows = 1;
		random = new Random();
		handler = new Handler();
		shakeAnimation = AnimationUtils.loadAnimation(this,R.anim.incorrect_shake);
		shakeAnimation.setRepeatCount(3);
		
		String[] regionNames = getResources().getStringArray(R.array.regionsList);
		for (String region : regionNames)
			regionsMap.put(region,  true);
		questionNumberTextView =(TextView) findViewById(R.id.questionNumberTextView);
		flagImageView = (ImageView) findViewById(R.id.flagImageView);
		buttonTableLayout = (TableLayout) findViewById(R.id.buttonTableLayout);
		answerTextView = (TextView) findViewById(R.id.answerTextview);
		questionNumberTextView.setText(getResources().getString(R.string.question) + "1" + getResources().getString(R.string.of) + "10");
		resetQuiz();
		}
	private void resetQuiz()
	{
		// TODO Auto-generated method stub
		AssetManager assets = getAssets();
		fileNameList.clear();
		try
		{
		    Set<String> regions = regionsMap.keySet();
				for (String region : regions)
				{
					if (regionsMap.get(region))
					{
						String[] paths = assets.list(region);
						for (String path : paths)
							fileNameList.add(path.replace(".png", ""));
					}
		         }
	     }
	 catch (IOException e)
	 { 
		 Log.e(TAG, "Error loading image file names",e);
	 }
	 }
	
	correctAnswers = 0;
	totalGuesses = 0;
	quizCountriesList.clear();
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_flag_qiz_game, menu);
		return true;
	}
 
}