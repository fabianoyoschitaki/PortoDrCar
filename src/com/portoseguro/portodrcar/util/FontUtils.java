package com.portoseguro.portodrcar.util;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {
	
	public enum FontEnum {
		POPCORN_MOUNTAIN("Popcorn_Mountain--Condensed.otf"),
		CHUNKFIVE("Chunkfive.otf");
		
		private String name;
		FontEnum(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
	}
	
	public static Typeface getFont(Context context, FontEnum font){
		return Typeface.createFromAsset(context.getAssets(), "fonts/" + font.getName());
	}
}
