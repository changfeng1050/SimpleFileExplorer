package com.example.changfeng.simplefileexplorer;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileExplorerActivity extends ActionBarActivity {

    private static final String TAG = "FileExplorerActivity";

    private List<FileInfo> fileInfoList = new ArrayList<>();

    private String backwardPath;
    private File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);

        initFileInfos();
        final FileListAdapter adapter = new FileListAdapter(FileExplorerActivity.this,
                R.layout.file_browser_item, fileInfoList );

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileInfo fileInfo = fileInfoList.get(position);
                Toast.makeText(getApplicationContext(),fileInfo.fileName, Toast.LENGTH_SHORT).show();

                if (fileInfo.isdir) {
                    Log.d(TAG, "into dir " + fileInfo.filePath);

                    backwardPath = fileInfo.filePath;

                    File file = new File(fileInfo.filePath + "/");
                    fileInfoList.clear();
                    getAllFiles(file);
                    adapter.notifyDataSetChanged();

                } else {

                }
            }
        });

        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backwardPath.isEmpty()) {
                    backwardPath = Environment.getExternalStorageDirectory().getPath();
                }

                int end = backwardPath.lastIndexOf('/');
                backwardPath = backwardPath.substring(0,end);

                Log.d(TAG, "backButton onClick backwardPath is" + backwardPath);
                getAllFiles(new File(backwardPath));
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initFileInfos() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory();
            Log.d(TAG, "path is " + path);
            getAllFiles(path);
        } else {
            Toast.makeText(this, "SD Card Not Found", Toast.LENGTH_LONG).show();
            finish();
        }



    }


    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
    private void getAllFiles(File root){
        fileInfoList.clear();
        Log.d(TAG, "getAllFiles called");
        File files[] = root.listFiles();
        if(files != null){
            for (File f : files){

                FileInfo fileInfo = new FileInfo(f.getName(), f.getPath(), f.isDirectory());
                fileInfoList.add(fileInfo);
            }
        }
    }

}
