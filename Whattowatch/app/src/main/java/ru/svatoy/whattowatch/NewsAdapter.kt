package ru.svatoy.whattowatch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.svatoy.whattowatch.databinding.NewListItemBinding

class NewsAdapter: ListAdapter<News, NewsAdapter.ItemHolderNews>(ItemComparator()){

    class ItemHolderNews(private val binding: NewListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(news: News) = with(binding){
            textNew.text = news.newsMessage
            nameNew.text = news.name
        }
        companion object{
            fun create(parent: ViewGroup): NewsAdapter.ItemHolderNews{
                return ItemHolderNews(NewListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
    }
    class ItemComparator : DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ItemHolderNews {
        return NewsAdapter.ItemHolderNews.create(parent)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ItemHolderNews, position: Int) {
        holder.bind(getItem(position))
    }
}