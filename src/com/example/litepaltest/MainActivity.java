package com.example.litepaltest;

import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;


import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = "MainActivity";
	
	Button btnSave,btnDelete,btnUpdate,btnQuery,createFile;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SQLiteDatabase db = Connector.getDatabase();
		btnSave = (Button)findViewById(R.id.btnSave);
		btnSave.setOnClickListener(this);
		btnDelete = (Button)findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);
		btnUpdate = (Button)findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(this);
		btnQuery = (Button)findViewById(R.id.btnQuery);
		btnQuery.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSave:
			News news = new News();
			news.setTitle("这是一条新闻标题");
			news.setContent("这是一条新闻内容");
			news.setPublishDate(new Date());
			Log.d(TAG, "news id is " + news.getId());
			news.save();
			/**
			 * LitePal对集合数据的存储提供专门的方法：DataSupport.saveAll(List<News> newsList);
			 */
			if(news.save()){
				Log.d(TAG, "news id is " + news.getId());
				Toast.makeText(MainActivity.this, "存储成功", Toast.LENGTH_LONG).show();
			}else{
			Toast.makeText(MainActivity.this, "存储失败", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.btnUpdate:
			//①contentValues的写法
			ContentValues values = new ContentValues();
			values.put("title", "今日IPhone7发布");
			DataSupport.update(News.class, values, 2);
			/**
			 * 修改某一条件下的所有数据：public static int updateAll(Class<?> modelClass, ContentValues values, String... conditions)
			 * 返回值表示此次修改影响了多少行数据
			 * @example:DataSupport.updateAll(News.class, values, "titles = ?", "今日IPhone7发布");
			 * @example:DataSupport.updateAll(News.class, values, "titles = ? and commentcount > ?", "今日IPhone7发布", "0");
			 */
			
//			//②不需要contentValues就能修改数据的写法
//			News updateNews = new News();
//			updateNews.setTitle("今日IPhone7发布");
//			updateNews.update(2);
//			/**
//			 * 同样可以实现条件约束:updateNews.updateAll("titles = ? and commentcount > ?", "今日IPhone7发布", "0");
//			 */
			break;
		case R.id.btnDelete:
			int deleteId = DataSupport.delete(News.class, 2);
			Toast.makeText(MainActivity.this, "删除的Id" + deleteId, Toast.LENGTH_LONG).show();
			/**
			 * 删除某一条件下的所有数据：public static int deleteAll(Class<?> modelClass, String... conditions) 
			 * 返回值表示此次删除影响了多少行数据
			 * @example:DataSupport.deleteAll(News.class, "titles = ?", "今日IPhone7发布");
			 * @example:DataSupport.deleteAll(News.class, "titles = ? and commentcount > ?", "今日IPhone7发布", "0");
			 */
			break;
		case R.id.btnQuery:
			News news1 = DataSupport.find(News.class, 1);
			Toast.makeText(MainActivity.this, "查询字段的标题：" + news1.getTitle(), Toast.LENGTH_LONG).show();
			/**
			 * 查询单条数据
			 * DataSupport.findLast()/DataSupport.findFirst()
			 * 查询多条数据
			 * DataSupport.findAll(News.class, ids):ids指定的id数组
			 * 连缀查询:缺点：查询时缺少groupby的API的封装，但是也可以写在where()函数中
			 * List<News> newsList = DataSupport.where("commentcount > ?", "0").find(News.class);
			 * List<News> newsList = DataSupport.select("title", "content")
			 * 				.where("commentcount > ?", "0")
			 * 				.order("publishdate desc").limit(10).offset(10)
			 * 				.find(News.class);
			 */
			break;
		default:
			break;
		}
	}
}
