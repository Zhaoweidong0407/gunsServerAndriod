package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Androiduser;
import cn.stylefeng.guns.modular.system.mapper.AndroiduserMapper;
import cn.stylefeng.guns.modular.system.model.params.AndroiduserParam;
import cn.stylefeng.guns.modular.system.model.result.AndroiduserResult;
import  cn.stylefeng.guns.modular.system.service.AndroiduserService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-09-05
 */
@Service
public class AndroiduserServiceImpl extends ServiceImpl<AndroiduserMapper, Androiduser> implements AndroiduserService {

    @Override
    public void add(AndroiduserParam param){
        Androiduser entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(AndroiduserParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(AndroiduserParam param){
        Androiduser oldEntity = getOldEntity(param);
        Androiduser newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public AndroiduserResult findBySpec(AndroiduserParam param){
        return null;
    }

    @Override
    public List<AndroiduserResult> findListBySpec(AndroiduserParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(AndroiduserParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Androiduser> objectQueryWrapper = new QueryWrapper<>();
        if (ToolUtil.isNotEmpty(param.getMobile())){
            objectQueryWrapper.like("mobile",param.getMobile());
        }
        objectQueryWrapper.orderByAsc("state");
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public int getCountByMobile(String mobile){
        QueryWrapper<Androiduser> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("mobile",mobile);
        int count = this.baseMapper.selectCount(objectQueryWrapper);
        return count;
    }

    @Override
    public Androiduser getUserByMobile(String mobile, String passWord) {
        QueryWrapper<Androiduser> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("mobile",mobile).eq("password",passWord);
        Androiduser user = this.baseMapper.selectOne(objectQueryWrapper);
        return user;
    }

    private Serializable getKey(AndroiduserParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Androiduser getOldEntity(AndroiduserParam param) {
        return this.getById(getKey(param));
    }

    private Androiduser getEntity(AndroiduserParam param) {
        Androiduser entity = new Androiduser();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
