package cn.ucai.test;

//package com.samboluong.javamodule;

//import com.samboluong.javamodule.entity.Address;

//import com.samboluong.javamodule.util.FileUtils;
//import com.samboluong.javamodule.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import cn.ucai.yidao.Address;

//import static com.samboluong.javamodule.AddressModule.Type.ADDRESSID;
//import static com.samboluong.javamodule.AddressModule.Type.DETAIL;
//import static com.samboluong.javamodule.AddressModule.Type.DISTRICT;
//import static com.samboluong.javamodule.AddressModule.Type.PINYIN;


/**
 * Created by SamBoLuong on 16/12/13.
 */

public class SunLiangLiang {

   //private static final String PATH = "/Users/SamBoLuong/Desktop/address.txt";

    private static List<Address> addressList;
    private static Address startAddress;
    private static Address endAddress;

    enum Type {
        ADDRESSID, DISTRICT, PINYIN, DETAIL
    }


    public static void main(String[] args) {
        //List<String> addressStrList = FileUtils.readFile2List(PATH);
        //saveAddress(addressStrList);
        showMenu();

        Scanner in = new Scanner(System.in);
        
        while (in.hasNext()) {
            handleInput(in);
            showMenu();
        }
    }


    // TODO: �Ż�
    private static void saveAddress(List<String> addressStrList) {
        addressList = new ArrayList<>();
        for (String s : addressStrList) {
            String[] splits = s.split("-");
            Address address = new Address();
            int i = 0;
           // if (i <= splits.length - 1) address.setAddressId(splits[i++]);
           // if (i <= splits.length - 1) address.setDistrict(splits[i++]);
          //  if (i <= splits.length - 1) address.setPinyin(splits[i++]);
          //  if (i <= splits.length - 1) address.setDetail(splits[i]);
            addressList.add(address);
        }
    }


    private static void handleInput(Scanner in) {
        switch (in.next()) {
            case "0":
                printAddressList(addressList);
                break;
            case "1":
                System.out.println("������id��");
               // findAddressByKey(addressList, in.next(), ADDRESSID);
                break;
            case "2":
                System.out.println("������������");
                //findAddressByKey(addressList, in.next(), DISTRICT);
                break;
            case "3":
                System.out.println("������ƴ����ĸ��");
               // findAddressByKey(addressList, in.next(), PINYIN);
                break;
            case "4":
                //System.out.println("��������ϸ������");
                //findAddressByKey(addressList, in.next(), DETAIL);
                break;
            case "5":
                startTaking();
                break;
            default:
                System.out.println("�Ƿ����룬���������룺");
                break;
        }
    }


    private static void startTaking() {
        selectStart();
        selectEnd();
        if (startAddress == null || endAddress == null) return;
        double distance = ThreadLocalRandom.current().nextDouble(1, 100);
        System.out.println("·�߹滮�ɹ����������" + distance + "��������ĵȴ�˾���ӳ���");
    }


    private static void selectEnd() {
        Scanner in = new Scanner(System.in);
        List<Address> findList;
        do {
            System.out.println("������Ŀ�ĵأ�");
            findList = findAddress(in.next());
        } while (findList.size() == 0);

        do {
            System.out.println("������IDѡ��");
            endAddress = selectAddressById(addressList, in.next());
        } while (endAddress == null);
    }


    private static void selectStart() {
        Scanner in = new Scanner(System.in);
        List<Address> findList;
        do {
            System.out.println("��������ʼ�أ�");
            findList = findAddress(in.next());
        } while (findList.size() == 0);

        do {
            System.out.println("������IDѡ��");
            startAddress = selectAddressById(addressList, in.next());
        } while (startAddress == null);
    }


    private static Address selectAddressById(List<Address> addressList, String id) {
       // if (!addressList.isEmpty() && !StringUtils.isEmpty(id)) {
            for (Address address : addressList) {
               // if (address.getAddressId().equals(id)) {
                    return address;
                }
         //   }
       // }
        return null;
    }


    private static List<Address> findAddress(String key) {
        List<Address> results = new ArrayList<>();
       // if (addressList.isEmpty() || StringUtils.isEmpty(key)) return results;
        for (Address add : addressList) {
         //   if (add.allWord().contains(key)) 
        	{
                results.add(add);
            }
        }
       // printAddressList(results);
       return results;
    }


    private static void findAddressByKey(List<Address> list, String key, Type type) {
       // if (list.isEmpty() || StringUtils.isEmpty(key)) return;
        List<Address> results = new ArrayList<>();

        String field;
        for (Address add : list) {
            if ((field = getField(add, type)) != null && field.contains(key)) {
                results.add(add);
            }
        }
        printAddressList(results);
    }


    private static String getField(Address address, Type type) {
        switch (type) {
            case ADDRESSID:
          //      return address.getAddressId();
            case DISTRICT:
           //     return address.getDistrict();
            case PINYIN:
           //     return address.getPinyin();
            case DETAIL:
           //     return address.getDetail();
            default:
                return "";
        }
    }


    private static void printAddressList(List<Address> list) {
        if (list.size() == 0) {
            System.out.println("�޽��");
            return;
        }

        for (Address address : list) {
            System.out.println(address);
        }
    }


    private static void showMenu() {
        System.out.println("���������ֽ�����ز�����\n" +
                "0.��ѯ����\n1.����id��ѯ\n2.����������ѯ\n3.��������ĸ\n4.������ϸ������ѯ\n5.��ʼ��");
    }


    private static List<Address> findAllAddress() {
        return addressList;
    }
    
    
    
    
    
}