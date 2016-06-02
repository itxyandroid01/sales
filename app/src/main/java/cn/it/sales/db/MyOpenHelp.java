package cn.it.sales.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/4.
 * 修改日期：2016/5/23添加方法
 *   （1）executeSQL
 *   (2)executeInsertSQLReturnRowId
 *   (3)executeSelectSQL
 */
public class MyOpenHelp extends SQLiteOpenHelper {
    private static final String TAG = "MyOpenHelp";
    static String mName = "sales.db";
    Context mContext;
    private static MyOpenHelp myOpenHelp;
    public static MyOpenHelp getInstance(Context context){
        if(myOpenHelp==null){
            myOpenHelp = new MyOpenHelp(context, mName, null, 1);
        }
        return myOpenHelp;
    }
    public MyOpenHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, mName, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<Map<String,Object>> examine(String sql){
        Cursor cursor;
        synchronized (MyOpenHelp.class) {
            SQLiteDatabase database = this.getReadableDatabase();
            cursor = database.rawQuery(sql, null);
        }
        //把数据转换部分放到锁外部
        return  cursorToList(cursor);
    }

    /**
     * 执行一个selece 查询语句用于替换examine方法
     * @param sql
     * @return
     */
    public List<Map<String,Object>> executeSelectSQL(String sql){
        Cursor cursor;
        synchronized (MyOpenHelp.class) {
            SQLiteDatabase database = this.getReadableDatabase();
            cursor = database.rawQuery(sql, null);
        }
        //把数据转换部分放到锁外部
        return  cursorToList(cursor);
    }

    private List<Map<String, Object>> cursorToList(Cursor cursor) {
     List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        while (cursor.moveToNext()){
            Map<String,Object> row= new HashMap<String, Object>();
            int ColumnCount=cursor.getColumnCount();
            for(int i=0;i<ColumnCount;i++){
//                String name=cursor.getColumnName(i).toLowerCase();
                String name=cursor.getColumnName(i);
                //String name=cursor.getString(i);
                int type=cursor.getType(i);
                switch(type){
                    case Cursor.FIELD_TYPE_BLOB:
                        row.put(name,cursor.getBlob(i));
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        row.put(name,cursor.getFloat(i));
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        row.put(name,cursor.getInt(i));
                        break;
                    case  Cursor.FIELD_TYPE_NULL:
                        row.put(name,null);
                        break;
                    case Cursor.FIELD_TYPE_STRING:
                        row.put(name,cursor.getString(i));
                        break;
                }
            }
            list.add(row);
        }
        return list;
    }
    public synchronized long add(String table, ContentValues contentValues){
        synchronized (MyOpenHelp.class) {
            SQLiteDatabase database=this.getWritableDatabase();
            return database.insert(table,null,contentValues);
        }
    }

    /*
    public boolean select(String mUserName) {
        // TODO Auto-generated method stub
        synchronized (MyOpenHelp.class) {
            SQLiteDatabase database = this.getReadableDatabase();
            String sql = "select examine from user where examine = ? ";
            Cursor cursor = database.rawQuery(sql, new String[]{mUserName});
        }
        if(cursor.moveToNext()){
            return false;
        }
        return true;
    }
    */

    /***
     *正常执行单条sql语句，执行正确 返回 1，
     * @param sql
     * @return执行正确 返回 1，错误返回 -1，
     */
    public int executeSQL(String sql){
        int row=-1;
        synchronized (MyOpenHelp.class) {
            //以写方式打开数据库
            SQLiteDatabase database = this.getWritableDatabase();
            try{
                row=0;
                //在try内批量执行SQL语句
                database.execSQL(sql);
                row++;
                    Log.d(TAG, "eexecuteSQL: " + sql);
            }catch (SQLException e){
                Log.d(TAG, "eexecuteSQL: "+e.getMessage());
                row=-1;
            }
        }
        return row;
    }

    /***
     *执行单条插入语句，并返回新插入行的 RowId编号
     * @param sql
     * @return RowId > 0 表示新插入行的ID，小于或者等于0 表示插入失败
     */
    public int executeInsertSQLReturnRowId(String sql){
        int row=-1;
        synchronized (MyOpenHelp.class) {
            //以写方式打开数据库
            SQLiteDatabase database = this.getWritableDatabase();
            try{
                row=0;
                //在try内批量执行SQL语句
                database.execSQL(sql);
                row++;
                sql = "select last_insert_rowid() as _id ";
                List<Map<String,Object>> mapList = examine(sql);
                Log.d("mapList","mapList"+mapList.size());
                if(mapList != null && mapList.size()>0){
                    int id=(int)mapList.get(0).get("_id");
                    Log.d("mapList","long"+id);
                }
                Log.d(TAG, "eexecuteSQL: " + sql);
            }catch (SQLException e){
                Log.d(TAG, "eexecuteSQL: "+e.getMessage());
                row=-1;
            }
        }
        return row;
    }

    /***
     * 在一个事务内，批量执行SQL语句，返回执行正确的条数
     * @param list
     * @return
     */
    public int batchExecuteSQL(List<String> list){
        int row=-1;
        synchronized (MyOpenHelp.class) {
            //以写方式打开数据库
            SQLiteDatabase database = this.getWritableDatabase();
            //开启一个事务
            database.beginTransaction();
            try{
                row=0;
                //在try内批量执行SQL语句
                for(String sql : list) {
                    database.execSQL(sql);
                    row++;
//                    Log.d(TAG, "batchExecuteSQL: "+sql);
                }

                //SQLite的事务默认为异常，所以批量操作完成时，设置事务位成功状态
                database.setTransactionSuccessful();
            }catch (SQLException e){
                Log.d(TAG, "batchExecuteSQL: "+e.getMessage());
                row=-1;
            }
            //无论有无异常，都结束事务
            database.endTransaction();
        }
        return row;
    }
    public void qingKongShuJu(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.beginTransaction();
        database.execSQL("delete from  t_jiaoban_shangpin where 1=1");
        database.setTransactionSuccessful();
        database.endTransaction();
    }

}
