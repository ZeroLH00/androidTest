package com.project.zero.uitest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private String[] animal = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private int[] picture = {R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat, R.drawable.elephant};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < animal.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("animal", animal[i]);
            listItem.put("picture", picture[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.simpleitem,
                new String[] {"animal", "picture"}, new int[] {R.id.animal, R.id.picture});
        ListView list = findViewById(R.id.mylist);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, animal[position], Toast.LENGTH_SHORT).show();

                TableLayout loginForm = (TableLayout)getLayoutInflater().inflate(R.layout.login, null);
                new AlertDialog.Builder(MainActivity.this).setTitle("自定义对话框").setView(loginForm).setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
            }
        });

        list.setAdapter(simpleAdapter);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // 为浮动上下文菜单登记视图，之后，长按视图则会触发上下文菜单的创建
            registerForContextMenu(list);
        } else {
            // 上下文操作模式下设置ListView的多选模式
            list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);


            list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                /**
                 * 在onCreateActionMode(ActionMode mode, Menu menu)方法之后，以及当前上下文操作栏需要刷新显示新数据时调用。
                 */
                @Override
                public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
                    return false;
                }

                /**
                 * 在用户退出上下文操作模式或所选菜单项操作已被响应，从而导致ActionMode对象将要销毁时调用。默认的实现会导致已选视图被反选。
                 * 这里，也可完成在上下文操作模式下，响应菜单操作而引发的相迎fragment更新。
                 */
                @Override
                public void onDestroyActionMode(ActionMode arg0) {
                }

                /**
                 * 在ActionMode对象创建后调用。也是实例化上下文菜单资源，并显示在上下文操作栏上的任务完成的地方。
                 */
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    /**
                     * 我们从操作模式，而非activity中获取MenuInflater的。操作模式负责对上下文操作栏进行配置。例如，
                     * 可调用ActionMode.setTitle(...)方法为上下文操作栏设置标题，而activity的MenuInflater则做不到这一点。
                     */
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.my_menu, menu);
                    return true;//这里必须返回true，否则会导致操作模式创建失败。
                }

                /*
                 * 在用户选中某个菜单项操作时调用。是响应上下文菜单项操作的地方。
                 */
                @Override
                public boolean onActionItemClicked(ActionMode mode,
                                                   MenuItem item) {
                    return false;
                }

                @Override
                public void onItemCheckedStateChanged(ActionMode mode,
                                                      int position, long id, boolean checked) {
                }
            });

        }




    }




/*

    @Override
    public  boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView animal = findViewById(R.id.animal);
        switch (item.getItemId()) {
            case R.id.item_font_10:
                animal.setTextSize(10);
                item.setChecked(true);
                return true;
            case R.id.item_font_16:
                animal.setTextSize(16);
                item.setChecked(true);
                return true;
            case R.id.item_font_20:
                animal.setTextSize(20);
                item.setChecked(true);
                return true;
            case R.id.item_color_black:
                animal.setTextColor(this.getResources().getColor(R.color.colorblack));
                item.setChecked(true);
                return true;
            case R.id.item_color_red:
                animal.setTextColor(this.getResources().getColor(R.color.colorred));
                item.setChecked(true);
                return true;
            case R.id.item_normal:
                Toast.makeText(MainActivity.this, "普通菜单项", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return true;
        }
    }
*/



}

