package com.ou.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ou.mall.bean.Product;
import com.ou.mall.bean.ProductExample;
import com.ou.mall.bean.ProductImg;
import com.ou.mall.bean.ProductImgExample;
import com.ou.mall.bean.UserAvatar;
import com.ou.mall.bean.UserAvatarExample;
import com.ou.mall.dao.ProductImgMapper;
import com.ou.mall.dao.ProductMapper;

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	ProductImgMapper productImgMapper;
	
	public void addProduct(Product product) {
		productMapper.insert(product);
	}

	public List<Product> getAll() {
		List<Product> selectByExample = productMapper.selectByExample(null);		
		
		return selectByExample;
	}

	public void uploadMainPic(Integer pid, String productImg) {
		Product record = new Product();
		record.setProductImg(productImg);

		ProductExample example = new ProductExample();
		example.createCriteria().andProductIdEqualTo(pid);
		productMapper.updateByExampleSelective(record, example);
		return;
	}

	public void createProductImg(Integer pid){
		ProductImgExample example = new ProductImgExample();
		example.createCriteria().andImgPidEqualTo(pid);

		if (productImgMapper.selectByExample(example).isEmpty()){
			ProductImg productImg = new ProductImg();
			productImg.setImgPid(pid);
			productImgMapper.insert(productImg);
		}
	}

	public void uploadSecPic(Integer pid, String imgImg2) {
		createProductImg(pid);
		
		ProductImg record = new ProductImg();
		record.setImgImg2(imgImg2);
		
		ProductImgExample example = new ProductImgExample();
		example.createCriteria().andImgPidEqualTo(pid);
		productImgMapper.updateByExampleSelective(record, example);
	}

	public void uploadThiPic(Integer pid, String imgImg3) {
		createProductImg(pid);
		
		ProductImg record = new ProductImg();
		record.setImgImg3(imgImg3);
		
		ProductImgExample example = new ProductImgExample();
		example.createCriteria().andImgPidEqualTo(pid);
		productImgMapper.updateByExampleSelective(record, example);		
	}

	public void updateProduct(Product product) {
		productMapper.updateByPrimaryKeySelective(product);
	}

	public void delProduct(Product product) {
		productMapper.deleteByPrimaryKey(product.getProductId());
	}

}