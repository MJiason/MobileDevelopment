package ua.kpi.comsys.io8214.mobileapp.ui.images;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ua.kpi.comsys.io8214.mobileapp.R;


class ImgAdapter extends ArrayAdapter<List<String>> {
    private List<List<String>> taskImg;
    Activity mainActivity;

    ImgAdapter(Context context, int textViewResourceId, List<List<String>> objects, Activity mainActivity) {
        super(context, textViewResourceId, objects);
        this.taskImg = objects;
        this.mainActivity = mainActivity;
        System.out.println("TASK img:"+taskImg.toString());;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.image_view, parent, false);
        String currentImageName;

        ImageView image1 = row.findViewById(R.id.image_item1);
        ImageView image2 = row.findViewById(R.id.image_item2);
        ImageView image3 = row.findViewById(R.id.image_item3);
        ImageView image4 = row.findViewById(R.id.image_item4);
        ImageView image5 = row.findViewById(R.id.image_item5);
        ImageView image6 = row.findViewById(R.id.image_item6);
        ImageView image7 = row.findViewById(R.id.image_item7);
        ImageView image8 = row.findViewById(R.id.image_item8);
        ImageView image9 = row.findViewById(R.id.image_item9);

        ProgressBar bar1 = row.findViewById(R.id.progress_bar_1);
        ProgressBar bar2 = row.findViewById(R.id.progress_bar_2);
        ProgressBar bar3 = row.findViewById(R.id.progress_bar_3);
        ProgressBar bar4 = row.findViewById(R.id.progress_bar_4);
        ProgressBar bar5 = row.findViewById(R.id.progress_bar_5);
        ProgressBar bar6 = row.findViewById(R.id.progress_bar_6);
        ProgressBar bar7 = row.findViewById(R.id.progress_bar_7);
        ProgressBar bar8 = row.findViewById(R.id.progress_bar_8);
        ProgressBar bar9 = row.findViewById(R.id.progress_bar_9);

        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        imageViews.add(image4);
        imageViews.add(image5);
        imageViews.add(image6);
        imageViews.add(image7);
        imageViews.add(image8);
        imageViews.add(image9);

        List<ProgressBar> bars = new ArrayList<>();
        bars.add(bar1);
        bars.add(bar2);
        bars.add(bar3);
        bars.add(bar4);
        bars.add(bar5);
        bars.add(bar6);
        bars.add(bar7);
        bars.add(bar8);
        bars.add(bar9);

        int photosNum = taskImg.get(position).size();

        for (int i=0; i<9; i++){
            try {
                if (i<photosNum){
                    ImgLoagFromStorage handler = new ImgLoagFromStorage(imageViews.get(i), mainActivity, position, getContext(), taskImg.get(position).get(i));
                    Thread thread = new Thread(handler);
                    thread.start();
                }
                else bars.get(i).setVisibility(View.INVISIBLE);
            } catch (Exception ignored){}
        }

        return row;
    }

    public static class ImgLoagFromStorage implements Runnable {
        protected ImageView imageView;
        protected Activity uiActivity;
        protected Context context;
        protected int position;
        protected String fileName;

        public ImgLoagFromStorage(ImageView imageView, Activity uiActivity, int position, Context context, String fileName) {
            this.imageView = imageView;
            this.uiActivity = uiActivity;
            this.position = position;
            this.context = context;
            this.fileName = fileName;
        }

        public void run() {
            try {
                File imageFile = new File(context.getFilesDir() + "/" + fileName);
                InputStream is = new FileInputStream(imageFile);

                Bitmap userImage = BitmapFactory.decodeStream(is);

                uiActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(userImage); // установка фото
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
