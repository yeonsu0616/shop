package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
<<<<<<< HEAD
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
=======
>>>>>>> 51aaf60b0e6fd3624ab10b6c51aef630e6f8cf71
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
>>>>>>> 51aaf60b0e6fd3624ab10b6c51aef630e6f8cf71
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        //상품등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);
        //이미지 등록
        for(int i=0; i<itemImgFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if(i==0)
                itemImg.setRepImgYn("Y");
            else
                itemImg.setRepImgYn("N");
            itemImgService.saveItemImg(itemImg,itemImgFileList.get(i));
        }
        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long itemId){
        //Entity
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        System.out.println(itemImgList.size());

        //DB에서 데이터를 가지고 옴
        //DTO
        List<ItemImgDto> itemImgDtoList = new ArrayList<>(); //왜 DTO를 만들었나요??

        for(ItemImg itemimg : itemImgList){
            //Entity -> DTO
            System.out.println(itemimg.getOriImgName());
            ItemImgDto itemImgDto = ItemImgDto.of(itemimg);
            System.out.println(itemImgDto.getOriImgName());
            itemImgDtoList.add(itemImgDto);
        }
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        //Item -> ItemFormDto modelMapper
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }
    public Long updateItem(ItemFormDto itemFormDto,List<MultipartFile>itemImgFileList) throws Exception{
        //상품변경
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);
        //상품 이미지 변경
        List<Long>itemImgIds = itemFormDto.getItemImgIds();

        for(int i=0; i<itemImgFileList.size(); i++){
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }
        return item.getId();
    }
<<<<<<< HEAD
    @Transactional(readOnly = true) //쿼리문 실행. 읽기만 함.
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDto,pageable);
    }
    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItempage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getMainItempage(itemSearchDto,pageable);
    }
=======
>>>>>>> 51aaf60b0e6fd3624ab10b6c51aef630e6f8cf71

}
