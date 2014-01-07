package com.sai.customimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class ImageViewCircle extends ImageView {
   
	private CircleConfig cirleConfig = null;

	public ImageViewCircle(Context context) {
			super(context);
			
			// TODO Auto-generated constructor stub
	}
	 
	public ImageViewCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// TODO Auto-generated constructor stub
	}

	

    public ImageViewCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	BitmapDrawable drawable = (BitmapDrawable) getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return; 
        }

        Bitmap originalBitmap = drawable.getBitmap();

        BitmapShader shader;
        shader = new BitmapShader(originalBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
         
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        
       float circleCenterX = originalBitmap.getWidth() *0.5f;
       float circleCenterY = originalBitmap.getHeight() *0.5f;
       
       float radius=circleCenterX<=circleCenterY?circleCenterX:circleCenterY;
      
       canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
       canvas.drawCircle(circleCenterX, circleCenterY, radius, paint);
        
       if(cirleConfig != null && cirleConfig.show){
    	   final Paint cpaint = new Paint();
    	   cpaint.setAntiAlias(true);
    	   cpaint.setStrokeWidth(cirleConfig.circleWidth);
    	   cpaint.setStyle(Paint.Style.STROKE);
    	   cpaint.setColor(cirleConfig.circleColor);
    	   canvas.drawCircle(circleCenterX, circleCenterY, (float)(radius-cirleConfig.circleWidth/2), cpaint);
       }
        
    }
    public CircleConfig getCirleConfig() {
		return cirleConfig;
	}

	public void setCirleConfig(CircleConfig cirleConfig) {
		this.cirleConfig = cirleConfig;
	}

	public static class CircleConfig{
		public boolean show = false;
		public float circleWidth = 3f;
		public int circleColor = Color.BLUE;
	}
    
  

    
    
}