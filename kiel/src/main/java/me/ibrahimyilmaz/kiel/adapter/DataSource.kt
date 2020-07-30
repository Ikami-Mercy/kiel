package me.ibrahimyilmaz.kiel.adapter

import androidx.recyclerview.widget.RecyclerView
import me.ibrahimyilmaz.kiel.item.Renderer
import java.lang.ref.WeakReference

abstract class DataSource<T : Any, A : RecyclerView.Adapter<*>>(
    private val renderers: Map<Class<out T>, Renderer<T>>
) {
    private val viewTypeToRendererKeyMap = renderers
        .map {
            it.value.itemViewType to it.key
        }.toMap()

    internal var recyclerViewAdapter = WeakReference<A>(null)

    fun attachToAdapter(adapter: A) {
        recyclerViewAdapter = WeakReference(adapter)
    }

    fun getRendererOf(
        viewType: Int
    ) = requireNotNull(renderers[viewTypeToRendererKeyMap[viewType]])
}

