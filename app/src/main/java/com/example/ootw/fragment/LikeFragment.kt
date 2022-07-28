package com.example.ootw.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.ootw.R
import com.example.ootw.adapter.PostListRVAdapter
import com.example.ootw.model.Post
import kotlinx.android.synthetic.main.fragment_like.*
import kotlinx.android.synthetic.main.fragment_like.view.*

class LikeFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    lateinit var postAdapter: PostListRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_like, container, false)
        // rv ver1: 화면에 아무것도 안뜸
//        view?.rv_Like_post?.adapter = LikeFragmentRecyclerViewAdapter()
//        view.rv_Like_post?.layoutManager = GridLayoutManager(requireActivity(), 3)
//        LikeFragmentRecyclerViewAdapter().getLog()

        // rv ver2: null pointer exception...
//        var imgUrl = "https://cdn.pixabay.com/photo/2021/08/03/07/03/orange-6518675_960_720.jpg"
//        var posts = ArrayList<Post>()
//        posts.add(Post("test1", "", "오늘은 참 덥네요!", imgUrl, 26, "나시", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"))
//        posts.add(Post("test2", "", "오늘은 참 덥네요!!!", imgUrl, 28, "반팔", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"),)
//        posts.add(Post("test3", "", "오늘은 참 덥네요!@@@", imgUrl, 29, "반바지", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"),)
//        posts.add(Post("test4", "", "오늘은 참 덥네요~~!!", imgUrl, 27, "반팔셔츠", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"),)
//
//        val adapter = PostListRVAdapter(posts)
//        rv_Like_post.adapter = adapter


//        initRecycler()
        return view
    }

//  뒤로가기 버튼 실행시
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("TestLog", "백프레스")
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_screen_panel, HomeFragment())?.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun initRecycler() {
//        postAdapter = PostListRVAdapter(requireContext())
//        rv_Like_post.adapter = postAdapter

//        var imgUrl = "https://cdn.pixabay.com/photo/2021/08/03/07/03/orange-6518675_960_720.jpg"
//        posts.apply {
//            add(Post("test1", "", "오늘은 참 덥네요!", imgUrl, 26, "나시", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"),)
//            add(Post("test2", "", "오늘은 참 덥네요!!!", imgUrl, 28, "반팔", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"),)
//            add(Post("test3", "", "오늘은 참 덥네요!@@@", imgUrl, 29, "반바지", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"),)
//            add(Post("test4", "", "오늘은 참 덥네요~~!!", imgUrl, 27, "반팔셔츠", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"),)
//            postAdapter.posts = posts
//            postAdapter.notifyDataSetChanged()
//        }
//        posts.apply { postAdapter.posts = posts
//            postAdapter.notifyDataSetChanged() }
    }

//    inner class LikeFragmentRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//        var imgUrl = "https://cdn.pixabay.com/photo/2021/08/03/07/03/orange-6518675_960_720.jpg"
//        var postDTOs : ArrayList<Post> = arrayListOf(
//            Post("test1", "", "오늘은 참 덥네요!", imgUrl, 26, "나시", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", "1"),
//        )
//
//        fun getLog() {
//            Log.d("ViewAdapter-LikeFragment", "Post: "+ postDTOs[0].body)
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//            var width = resources.displayMetrics.widthPixels / 3
//
//            var imageview = ImageView(parent.context)
//            imageview.layoutParams = LinearLayoutCompat.LayoutParams(width, width)
//            return CustomViewHolder(imageview)
//        }
//
//        inner class CustomViewHolder(var imageview: ImageView): RecyclerView.ViewHolder(imageview) {
//
//        }
//
//        override fun getItemCount(): Int {
//            return postDTOs.size
//        }
//
//        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//            var imageview = (holder as CustomViewHolder).imageview
//            Glide.with(holder.itemView.context).load(postDTOs[position].imgURL)
//                .apply(RequestOptions().centerCrop()).into(imageview)
//            Log.d("LikeFragment", "post: "+postDTOs[position].id)
//
//        }
//    }
}