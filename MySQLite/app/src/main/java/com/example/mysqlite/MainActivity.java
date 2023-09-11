//資管三甲 C109118142 徐敏容

package com.example.mysqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Context context;
    private ListView listView = null;
    private ImageButton fab;
    private DBHelperMenu dbHelper;
    private SQLiteDatabase db;
    private SimpleCursorAdapter adapter;
    private Cursor maincursor; // 記錄目前資料庫查詢指標

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        dbHelper = new DBHelperMenu(context);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.listView);
        listView.setEmptyView(findViewById(R.id.emptyView));
        listView.setOnItemClickListener(new MyOnItemClickListener());

        fab = (ImageButton) findViewById(R.id.fab);
        // 實作視圖外觀
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                // 設定視圖尺寸
                int size = 56;  // 56dp
                // 將視圖外觀設成橢圓形（本例是設成圓形）
                outline.setOval(0, 0, size, size);
            }
        };
        // 設定 fab（ImageButton）外框樣式
        fab.setOutlineProvider(viewOutlineProvider);
        // 註冊 fab onclick 事件
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // to create AlertDialog for adding items...
                AlertDialog dialog = addMenuItemDialog();
                dialog.show();
            }
        });

        // 重新整理ListView
        refreshListView();
    }

    // 重新整理ListView（將資料重新匯入）
    private void refreshListView() {
        if (maincursor == null) {
            // 1.取得查詢所有資料的cursor
            maincursor = db.rawQuery(
                    "SELECT _id, title, price, image FROM menu_list", null);
            // 2.設定ListAdapter適配器(使用SimpleCursorAdapter)
            adapter = new SimpleCursorAdapter(context, R.layout.item_info_row,
                    maincursor,
                    new String[] { "_id", "title", "price", "image" },
                    new int[] { R.id.itemId, R.id.itemTitle, R.id.itemPrice,
                            R.id.itemImage },
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            // 3.注入適配器
            listView.setAdapter(adapter);
        } else {
            if (maincursor.isClosed()) { // 若 requery()不會檢查 cursor closed 的問題時...
                maincursor = null;
                refreshListView();
            } else {
                maincursor.requery(); // 若資料量大時，建議改用 CursorLoader
                adapter.changeCursor(maincursor);
                adapter.notifyDataSetChanged();
            }
        }
    }

    // 新增商品記錄對話視窗
    private AlertDialog addMenuItemDialog() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AlertDialog);

        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        View layout = inflater.inflate(R.layout.item_spinner,
                (ViewGroup) findViewById(R.id.root));

        // 自定View
        final Spinner item_spinner = (Spinner) layout
                .findViewById(R.id.item_spinner);
        final TextView item_price = (TextView) layout
                .findViewById(R.id.item_price);
        item_spinner.setAdapter(new MenuListAdapter(context,
                getMenuItemList()));

        builder.setTitle(R.string.add_item);
        builder.setIcon(android.R.drawable.ic_input_add);
        builder.setView(layout);
        builder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ItemList new_item = (ItemList) item_spinner
                                .getSelectedItem();

                        // 將 new_item 新增到資料表的參數
                        ContentValues cv = new ContentValues();
                        cv.put("title", new_item.getTitle());
                        cv.put("price", Integer.parseInt(item_price.getText().toString()));
                        cv.put("image", new_item.getImage());

                        // 執行SQL語句
                        long id = db.insert("menu_list", null, cv);
                        Toast.makeText(context, "_id：" + id, Toast.LENGTH_SHORT).show();

                        // 將資料匯入ListView
                        refreshListView();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                });

        return builder.create();
    }

    // 修改/刪除商品對話視窗
    private void modifyMenuItemDialog(final Cursor cursor) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AlertDialog);

        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        View layout = inflater.inflate(R.layout.item_spinner,
                (ViewGroup) findViewById(R.id.root));

        // 自定View
        final Spinner item_spinner = (Spinner) layout
                .findViewById(R.id.item_spinner);
        final TextView item_price = (TextView) layout
                .findViewById(R.id.item_price);

        MenuListAdapter menuListAdapter = new MenuListAdapter(context,
                getMenuItemList());
        item_spinner.setAdapter(menuListAdapter);

        item_price.setText(cursor.getString(2));
        item_spinner.setSelection(menuListAdapter.getPosition(cursor
                .getString(1)));
        final String update_cursor = cursor.getString(0);

        builder.setTitle(R.string.update_delete_product);
        builder.setIcon(android.R.drawable.ic_input_add);
        builder.setView(layout);
        builder.setPositiveButton(R.string.modify,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ItemList item_selected = (ItemList) item_spinner
                                .getSelectedItem();

                        // 將修改記錄回存到資料表中指定資料列
                        ContentValues cv = new ContentValues();
                        cv.put("title", item_selected.getTitle());
                        cv.put("price", Integer.parseInt(item_price.getText().toString()));
                        cv.put("image", item_selected.getImage());

                        // 執行SQL修改語句
                        int rowcount = db.update("menu_list", cv, "_id=?",
                                new String[]{update_cursor});
                                // new String[]{cursor.getString(0)});
                        Toast.makeText(context,
                                "異動筆數：" + rowcount + cursor.getString(0),
                                Toast.LENGTH_SHORT).show();

                        refreshListView();
                        dialog.dismiss();
                    }
                });
        builder.setNeutralButton(R.string.delete,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 執行SQL刪除語句，刪除資料表中指定資料列
                        int rowcount = db.delete("menu_list", "_id=?",
                                new String[]{cursor.getString(0)});
                        Toast.makeText(context, "異動筆數：" + rowcount + " " +
                                cursor.getString(0), Toast.LENGTH_SHORT).show();

                        refreshListView();
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

    // 取得資料清單
    private List<ItemList> getMenuItemList() {

        List<ItemList> data = new ArrayList<ItemList>();
		data.add(new ItemList("Latte", R.drawable.coffee_latte));
		data.add(new ItemList("Cappuccino", R.drawable.coffee_cappuccino));

        data.add(new ItemList("吉事漢堡", R.drawable.m_cheeseburger));
        data.add(new ItemList("大麥克", R.drawable.m_bigmac));
        data.add(new ItemList("1955 美式培根牛肉堡", R.drawable.m_bacon));
        data.add(new ItemList("雙層四盎司牛肉堡", R.drawable.m_double_four_ounces));
        data.add(new ItemList("麥克鷄塊(4塊)", R.drawable.m_nuggets));
        data.add(new ItemList("玉米濃湯", R.drawable.soup));
        data.add(new ItemList("冰炫風", R.drawable.ice));
        return data;
    }

    // OnItemClick 監聽器
    private class MyOnItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // 取得 Cursor
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            // to create AlertDialog for modifying items...
            modifyMenuItemDialog(cursor);
        }
    }
}
