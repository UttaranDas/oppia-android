package org.oppia.android.app.home.recentlyplayed

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.oppia.android.app.fragment.FragmentComponentImpl
import org.oppia.android.app.fragment.InjectableFragment
import org.oppia.android.app.model.PromotedStory
import javax.inject.Inject

private const val RECENTLY_PLAYED_FRAGMENT_INTERNAL_PROFILE_ID_KEY =
  "RecentlyPlayedFragment.internal_profile_id"

/** Fragment that contains all recently played stories. */
class RecentlyPlayedFragment : InjectableFragment(), OngoingStoryClickListener {
  companion object {
    const val TAG_RECENTLY_PLAYED_FRAGMENT = "TAG_RECENTLY_PLAYED_FRAGMENT"

    /** Returns a new [RecentlyPlayedFragment] to display recently played stories. */
    fun newInstance(internalProfileId: Int): RecentlyPlayedFragment {
      val recentlyPlayedFragment = RecentlyPlayedFragment()
      val args = Bundle()
      args.putInt(RECENTLY_PLAYED_FRAGMENT_INTERNAL_PROFILE_ID_KEY, internalProfileId)
      recentlyPlayedFragment.arguments = args
      return recentlyPlayedFragment
    }
  }

  @Inject
  lateinit var recentlyPlayedFragmentPresenter: RecentlyPlayedFragmentPresenter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (fragmentComponent as FragmentComponentImpl).inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val args =
      checkNotNull(arguments) { "Expected arguments to be passed to RecentlyPlayedFragment" }
    val internalProfileId = args.getInt(RECENTLY_PLAYED_FRAGMENT_INTERNAL_PROFILE_ID_KEY, -1)
    return recentlyPlayedFragmentPresenter.handleCreateView(inflater, container, internalProfileId)
  }

  override fun onOngoingStoryClicked(promotedStory: PromotedStory) {
    recentlyPlayedFragmentPresenter.onOngoingStoryClicked(promotedStory)
  }
}
