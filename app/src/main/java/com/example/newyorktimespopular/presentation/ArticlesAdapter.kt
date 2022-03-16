package com.example.newyorktimespopular.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newyorktimespopular.R
import com.example.newyorktimespopular.model.Article


class ArticlesAdapter(var context: Context, articleClick: ArticleClick) :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {
    private var articles: List<Article>? = null
    private var articleClick:ArticleClick = articleClick
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.article_itemview, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.titleTextView.text = articles!![position].title
        holder.descTextView.text = articles!![position].byline
        holder.dateTextView.text=articles!![position].published_date
        var imageUrl="https://www.industry.gov.au/sites/default/files/August%202018/image/news-placeholder-738.png"

        if(articles!![position].media.isNotEmpty()&&articles!![position].media[0].media_metadata!=null&&articles!![position].media[0].media_metadata!!.isNotEmpty()){
            imageUrl=articles!![position].media[0].media_metadata!![0].url.toString()
       }

        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .into(holder.avatarImageView)



        holder.itemView.setOnClickListener {
            articleClick.onClick(articles!![position])

        }



    }

    override fun getItemCount(): Int {
        return if (articles == null) 0 else articles!!.size
    }

    fun setArticles(list: List<Article>?) {
        articles = list
        notifyDataSetChanged()
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.title_tv)
        var avatarImageView: ImageView = itemView.findViewById(R.id.article_image)
        var descTextView:TextView =itemView.findViewById(R.id.description_tv)
        var dateTextView:TextView =itemView.findViewById(R.id.date)

    }
}
interface ArticleClick{
    fun onClick( article:Article)
}