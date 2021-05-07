package ua.kpi.comsys.io8214.mobileapp.ui.images;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ua.kpi.comsys.io8214.mobileapp.R;

import static android.app.Activity.RESULT_OK;

public class ImageFragment extends Fragment {
    View root;
    List<List<String>> images = new ArrayList<>();
    private final int success = 1;
    private ImgAdapter adapter;
    ListView listView;
    Random random = new Random();
    Boolean tab_open = false;
    String imagesListName = "ImagesList";

    public String makeStringFromList(List<List<String>> img){
        StringBuilder result = new StringBuilder();
        for (List<String> obj1 : img)
            for (int i = 0; i < obj1.size(); i++) {
                result.append(obj1.get(i));
                if (i!=obj1.size()-1)
                    result.append(";");
            }
        return result.toString();
    }

    public List<List<String>> makeListFromString(String imgStr){
        List<List<String>> result = new ArrayList<>();
        List<String> firstStep = new ArrayList<String>(Arrays.asList(imgStr.split(";")));

        if (imgStr.equals(""))
            return result;

        for (String obj : firstStep) {
            if (result.size() == 0) {
                List<String> tempImageList = new ArrayList<>();
                result.add(tempImageList);
            }
            if (result.get(result.size() - 1).size() >= 9) {
                List<String> tempImageList = new ArrayList<>();
                tempImageList.add(obj);
                result.add(tempImageList);
            } else {
                result.get(result.size() - 1).add(obj);
            }
        }
        return result;
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println(">>>STOP");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tab_open=false;
        System.out.println(">>>DESTROY");
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences settings = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(imagesListName, makeStringFromList(images));

        editor.apply();
        System.out.println(">>>PAUSE");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!tab_open) {
            SharedPreferences settings = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
            images = makeListFromString(settings.getString(imagesListName, ""));
            System.out.println("Maked images: " + images.toString());
            if (images != null & images.size() > 0) {
                if (images.get(0).size() > 0) {
                    adapter = new ImgAdapter(getActivity(), R.layout.image_view, images, getActivity());
                    listView.setAdapter(adapter);
                } else
                    images = new ArrayList<>();
            } else
                images = new ArrayList<>();

            tab_open = true;
        }
        System.out.println(">>>RESUME");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(">>>CREATE");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.images_fragment, container, false);
        FloatingActionButton addImageButton = root.findViewById(R.id.addImage);
        listView = root.findViewById(R.id.imagesList);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, success);
            }
        });

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (!tab_open){
            tab_open = true;
            System.out.println("TAB FIRST OPEN");
        }
        else
            System.out.println("TAB MOVE");

        if (requestCode == success & imageReturnedIntent!=null) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = imageReturnedIntent.getData();
                    final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage1 = BitmapFactory.decodeStream(imageStream);

                    String newImageName = "img"+imageUri.hashCode()+"_"+(random.nextInt(10000)) + ".jpeg";

                    if (images != null){
                        if (images.size()==0){
                            List<String> tempImageList = new ArrayList<>();
                            images.add(tempImageList);
                        }
                        if (images.get(images.size()-1).size()>=9){
                            List<String> tempImageList = new ArrayList<>();
                            tempImageList.add(newImageName);
                            images.add(tempImageList);
                        }
                        else {
                            images.get(images.size()-1).add(newImageName);
                        }
                    }

                    ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
                    selectedImage1.compress(Bitmap.CompressFormat.JPEG, 30, bos2);
                    byte[] bitmapdata = bos2.toByteArray();
                    File imageFile = new File(getContext().getFilesDir(), newImageName);

                    try {
                        FileOutputStream fos = new FileOutputStream(imageFile);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
//                        requireActivity().recreate();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("IMAGES: "+images.toString());

                    if(images != null & images.get(0).size()==1){
                        adapter = new ImgAdapter(getActivity(), R.layout.image_view, images, getActivity());

                        listView.setAdapter(adapter);
                        System.out.println("SET ADAPTER");
//                        requireActivity().recreate();

                    }
                    else if (images.size()>0){
                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
