package com.portoseguro.portodrcar.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.portoseguro.portodrcar.R;

public class PortoDrCarSplashActivity extends Activity {

	private static final long TEMPO_SPLASH_SCREEN_MILIS = 3300;
	private static int TELA = 0;
	
	private ImageView logo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		logo = (ImageView) findViewById(R.id.logo);
		Drawable[] layers = new Drawable[2];
		layers[0] = getResources().getDrawable(R.drawable.porto_dr_car_before);
		layers[1] = getResources().getDrawable(R.drawable.porto_dr_car_after);
		TransitionDrawable transition = new TransitionDrawable(layers);
		logo.setImageDrawable(transition);
		transition.startTransition(1500);
		waitForActivityCall(TEMPO_SPLASH_SCREEN_MILIS);
		/**logo = (ImageView) findViewById(R.id.logo);
		logo.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				chamaTelaPrincipal();
				return false;
			}
		});
		logo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TELA == 0){
					logo.setImageDrawable(getResources().getDrawable(R.drawable.porto_dr_car));
					TELA++;
				} else if (TELA == 1){
					logo.setImageDrawable(getResources().getDrawable(R.drawable.porto_dr_car_2));
					TELA++;
				} else if (TELA == 2){
					logo.setImageDrawable(getResources().getDrawable(R.drawable.porto_dr_car_3));
					TELA = 0;
				}
			}
		});**/
	}
	
	/**
	 * Método que espera determinado tempo para fazer alguma ação
	 * @param timeToWait
	 */
	private void waitForActivityCall(long timeToWait) {
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chamaTelaPrincipal();
            }
        }, timeToWait);
	}

	/**
	 * Chama a tela principal da aplicação
	 */
	private void chamaTelaPrincipal(){
		final Intent mainIntent = new Intent(PortoDrCarSplashActivity.this, TelaPrincipalActivity.class);
        PortoDrCarSplashActivity.this.startActivity(mainIntent);
        overridePendingTransition(R.anim.trans_up_in, R.anim.trans_up_out);
        PortoDrCarSplashActivity.this.finish();
	}
}
