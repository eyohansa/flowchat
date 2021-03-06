# FlowChat

# Release 0.3.0
- Custom discussion sorting. [#9](https://github.com/tchoulihan/flowchat/issues/9).
- Added emoji support. [#8](https://github.com/tchoulihan/flowchat/issues/8).
- Added embedded videos. [#7](https://github.com/tchoulihan/flowchat/issues/7).
- Fixed non-image type links from being in a discussion component. [#14](https://github.com/tchoulihan/flowchat/issues/14).
- Figured out sub-comment bug. [#11](https://github.com/tchoulihan/flowchat/issues/11).
- Figured out markdown-refresh bug. [#10](https://github.com/tchoulihan/flowchat/issues/10).

# Release 0.2.0
- Fixing tab Refetching.[1](https://github.com/tchoulihan/flowchat/commit/96558421bef042c167acaa709d6e36cd30701b3c)
- Using angular rc4.[1](https://github.com/tchoulihan/flowchat/commit/bc7f6379691659fe8da20c01eb3c947ffe19d98b)
- When you log back in, repull your favorite discussions.[1](https://github.com/tchoulihan/flowchat/commit/fca846fe2ce6861d0c31da65ea9445241470cd3e)


# Finished
- Add favicon badge for unread messages
  - [example](https://github.com/ejci/favico.js)
  - Could only get this working through CDN and declare var any
- Smart comment scrolling, fetch size 20, max depth 20
- Add a version page
- Update Readme
  - Add pictures
  - Make discussion and comment data more realistic, and with pictures
    - Use star wars subreddit as an example
- Set up new repo on github called flowchat
  - Set up deployment with [travis-ci](https://neemzy.org/articles/
  deploy-to-your-own-server-through-ssh-with-travis-ci)
- Figure out how to get thumbnails from links.... might be tough for non-imgur-like things. 
  - Get working for imgur first. 
  - Add pictures in cards
- Build startup documentation, setting up postgres, git clone. 
- Add a paypal donate button
- Paginate / smart scrolling for both comments and discussions
  - got discussions, but comments I think I've gotta leave till later, uber complicated
- Add moderator abilities:
  - removing comments
  - blocking users
- Move sorting stuff to .properties file
- Top nav with bell alerts for if someone replied directly to your comment, clicking goes to that discussion comment parent. 
  - [Periodically fetching observable](http://www.codegur.online/36086596/periodically-updating-observable-value-in-angular2)
  - Add 'read' boolean to comments. 
  - [Good Notification Example](http://infinite-woodland-5276.herokuapp.com/index.html)
- Search bar, search for (public) discussions, or tags
  - Only got it for discussions
- Add trending algorithms [1](http://sorentwo.com/2013/12/30/let-postgres-do-the-work.html)
  - Possibly tunable for each thread creator?
  - comment and discussion ranking based on: 
    - recentness
    - # of votes
    - avg_score 
    - and weighting factors for all three
  - Have these be set up by the discussion mods?
- Replace alert with reconnect modal
- Add ability to delete your own comment(schema changes)
- Add ability to remove your own comment, without actually deleting the comment and its subtree
- Toastr / whatever messages for errors, message notifications
- Able to delete your own discussions
- Favorite/'watched discussions', anything you comment in is automatically added to favorites/saved.
- Serving multiple discussions
  - All discussions have a single moderator, whoever created the channel.
    - For private, any emails that aren't in the system should get emailed a link to the discussion.
  - Tags/Hashtags(with smart linking)(maximum of 3 - 5)
  - Discussions on left [1](http://v4-alpha.getbootstrap.com/examples/dashboard/)
  - Set up like Stash, [nodebb](https://github.com/NodeBB/NodeBB), reddit
  - Left bar will be all your discussions that aren't archived(x'd out)
  - When you comment in a discussion, it gets added to your left bar.
  - Don't want mods to be able to squat on tag names, want community moderation.
- private discussions
  - schema for private_discussion_user
  - add a thing similar to tag adding, but for private discussion users
- Clean up codebase
- Add discussion editing for owners
- Create discussion page
- HashTag pages, looks like front page but only with those tags
- Notifications, toastr [1](https://github.com/PointInside/ng2-toastr), or toasty: [2](http://akserg.github.io/ng2-webpack-demo/) [3](https://github.com/Stabzs/Angular2-Toaster)
- Fix login system, automatically set cookies, temp button for clear cookies, if auth is undefined, then don't show the top right user button.
- Links, or left bar with discussions? Are you making a reddit alternative, or a slack alternative? Team-based discussions, or thread based?
- Comment sort broadcasting for peoples votes? Seems excessive, but could be very useful for sorting. 
  - This is done, except for the resorting on the front end based on avgRank. 
  - Also avoids having to reload the page.
  - Would require resorting(and repulling) all the parents of that comment, just like editing.
  - Top level ones would require a full resort... seems excessive.
- Voting on discussions
- Add schema for discussion title, discussion link, discussion tags
- Fixed userbar on the right, that looks pretty
- Reconnect websocket if [disconnected](http://stackoverflow.com/questions/3479734/javascript-jquery-test-if-window-has-focus)
- Add # of vote count in addition to average ranking, put on the right side of the pill?
- Set up red to green gradient for input bar: linear-gradient(90deg, red, yellow) [1](https://css-tricks.com/styling-cross-browser-compatible-range-inputs-css/)
- Top level replying
	- Make it feel like chat, with a bottom expandable bar
- Automatic setting of cookies for anonymous users
- Refresh only specific changed content
  - For now, use newCommentId.
  - Automatically Scroll to new comment - location.href = "#myDiv";
  - Change them to a new highlighted color, then remove that.
  - Should you scroll when you are currently replying, or wait till after, or not at all?
  - Recursion: [1](http://stackoverflow.com/a/2549333/1655478) [2](http://stackoverflow.com/questions/16228467/how-do-i-break-out-of-loops-in-recursive-functions) [3](http://stackoverflow.com/questions/34522306/angular-2-focus-on-newly-added-input-element)
- Comment collapsing
- Start adding [bootstrap-markdown.](http://www.codingdrama.com/bootstrap-markdown/)
- A working user / user login system
  - Create an open system, new users are just labeled as anonymous_aId
  - Comment editing(on your own comments).
  - When user logs in, refresh the session. [BehaviorSubject](http://stackoverflow.com/questions/34376854/delegation-eventemitter-or-observable-in-angular2/35568924#35568924)
- Comment subset loading
  - What happens when you get too many comments?
    - Use comment_breadcrumbs_view where parent_id = [parent_id]
  - Do you not load new ones, if they aren't under your branch?
  - Get comment_threaded_view working for 
  - Implement a max depth based on how it looks, and a goto discussion button
  - [Route params](http://plnkr.co/edit/IcnEzZ0WtiaY5Bpqrq2Y?p=preview) [2](https://github.com/angular/angular/issues/6204)
  - Hierarchical data in SQL [1](http://stackoverflow.com/questions/8252323/mysql-closure-table-hierarchical-database-how-to-pull-information-out-in-the-c) [2](http://stackoverflow.com/questions/192220/what-is-the-most-efficient-elegant-way-to-parse-a-flat-table-into-a-tree/)
- Possibly add range voting?
	- Use default html sliders [1](http://stackoverflow.com/questions/15935837/how-to-display-a-range-input-slider-vertically) [styling](http://danielstern.ca/range.css/#/) [fiddle](http://jsfiddle.net/Mmgxg/)

  # TODO
- Discussion Trending by time spans
- Publish markdown-edit as library, [example](http://blog.angular-university.io/how-to-create-an-angular-2-library-and-how-to-consume-it-jspm-vs-webpack/).
- Add https support
- Figure out `autosize.min.js`, and `favico` issue, stop using them from a CDN.
- Get font awesome working locally
- Add periodic DB backups