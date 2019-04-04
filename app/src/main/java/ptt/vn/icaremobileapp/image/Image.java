package ptt.vn.icaremobileapp.image;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.ByteArrayOutputStream;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

/**
 * Created by kingpes on 8/20/18.
 */

public class Image {
    private static Image instance = null;

    public static Image getInstance() {
        if (instance == null) {
            instance = new Image();
        }
        return instance;
    }

    public void load(Bitmap bitmap, ImageView img){
        if (img.getContext() != null){
            GlideApp.with(img.getContext())
                    .load(bitmap)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(img);
        }
    }

    public void load(Uri uri, ImageView img){
        if (img.getContext() != null){
            GlideApp.with(img.getContext())
                    .load(uri)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(img);
        }
    }

    public void load(int drawable, ImageView img){
        if (img.getContext() != null){
            GlideApp.with(img.getContext())
                    .load(drawable)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(img);
        }
    }

    public void load(String url, ImageView img) {
        if (img != null && img.getContext() != null) {
            GlideApp.with(img.getContext())
                    .load(url)
                    //.override()
                    //.placeholder()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(img);
        }
    }

    public void save(Context context, String url, ImageView img) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .skipMemoryCache(false)  //No memory cache
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) //No disk cache
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArr = stream.toByteArray();


                        return false;
                    }
                })
                .into(img);
    }

    public void clearCache(final Context context) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        });
        thread.start();
    }

}
