package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.sskj.asset.AssetActivity;
import com.sskj.asset.ZoomActivity;
import java.lang.Override;
import java.lang.String;
import java.util.Map;

/**
 * DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY AROUTER. */
public class ARouter$$Group$$asset implements IRouteGroup {
  @Override
  public void loadInto(Map<String, RouteMeta> atlas) {
    atlas.put("/asset/manager", RouteMeta.build(RouteType.ACTIVITY, AssetActivity.class, "/asset/manager", "asset", null, -1, -2147483648));
    atlas.put("/asset/zoom", RouteMeta.build(RouteType.ACTIVITY, ZoomActivity.class, "/asset/zoom", "asset", null, -1, -2147483648));
  }
}
