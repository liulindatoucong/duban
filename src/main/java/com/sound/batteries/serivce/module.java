package com.sound.batteries.serivce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sound.batteries.utils.Base64;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.json.JsonSerializer;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhang yufei on 2018/7/3.
 */
public class module {

    //主机厂平台地址
    public static String ip1 = "http://118.31.218.203:8993";
    //主机厂调用接口令牌
    public static String vehicle_token="B1GrDYFkuLJYzZIVxZIJCoukHe04eGqB";
    //主机厂调用接口密钥
    public static String vehicle_key="2aFhsv5I9xaM7Z5i";
    //电池厂调用接口令牌
    public static String factory_token="B1GrDYFkuLJYzZIVxZIJCoukHe04eGqB";
    //电池厂调用接口密钥
    public static String factory_key="2aFhsv5I9xaM7Z5i";

    public static void main(String args[]) {
        long start = System.currentTimeMillis();

        //1.主机厂车辆生产
        //createVehiclePack();
        //2.电池厂电池生产
        //createBatteryPack();
        //3.主机厂车辆销售
        //createSale();
        //4.电池厂售后
        //createReplaceBattery();
        //5.主机厂维修
        //createReplaceRecord();
        //6.电池厂维修
        //createFactoryRepair();
        //7.主机厂回收网点入库
        //createRecoverStorage();
        //8.主机厂回收网点退役
        //createRetireVehicle();
        //9.主机厂车辆换电入库
        //createReplaceBatteryInStorage();
        //10.主机厂车辆换电记录
        //createReplaceReplacement();
        //11.主机厂车辆换电维修
        //createReplaceRepair();
        //12.主机厂车辆换电退役
        //createReplaceRetire();
        //13.电池厂电池退役
        //createRetireFactory();

        test();

        long end = System.currentTimeMillis();
        System.out.println("完成" + (end - start));
    }

    public static void createVehiclePack(){
        List<String> packList=new ArrayList<>();
        packList.add("P100");
        Map<String,Object> vinMap=new HashMap<>();
        vinMap.put("vin","LNBSCB3F0DD130955");
        vinMap.put("vehModelName","vehModel01");
        vinMap.put("packCodeList",packList);
        vinMap.put("systemCode","S001");
        vinMap.put("offlineProductionTime","2018-01-01");
        vinMap.put("vehicleBrand","奔驰");
        vinMap.put("vehicleName","奔驰");
        vinMap.put("vehicleType","1");
        List<Map<String,Object>> vinList=new ArrayList<>();
        vinList.add(vinMap);
        Map<String,Object> vehicleMap=new HashMap<>();
        vehicleMap.put("vinList",vinList);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/receiveVehicleProduce", vehicleMap, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createBatteryPack(){
        List<String> cellList1=new ArrayList<>();
        cellList1.add("C111");
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M110");
        module1.put("cellList",cellList1);
        module1.put("modelId","zhangM");
        module1.put("cellModelId","zhangC");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P100");
        pack1.put("moduleList",moduleList1);
        pack1.put("serial","1");
        pack1.put("modelId","zhangP");
        pack1.put("systemId","S001");
        pack1.put("systemModelId","zhangS");
        pack1.put("orderNo","o001");
        List<Map<String,Object>> packList=new ArrayList<>();
        packList.add(pack1);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/vehicle/receiveBatteryProduce", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createSale(){
        List<Map<String,Object>> vehicleList=new ArrayList<>();
        Map<String,Object> vehicle=new HashMap<>();
        vehicle.put("vin", "LNBSCB3F0DD130955");
        vehicle.put("licensePlate", "京BZ0418");
        vehicle.put("vin", "LNBSCB3F0DD130955");
        vehicle.put("vehTypeName", "111808");
        vehicle.put("saleTime", "2018-01-05");
        vehicle.put("saleArea", "北京市昌平区");
        vehicle.put("ownerName", "张君宝");
        vehicle.put("Idnum","150204197111111810");
        vehicle.put("epname", "吉利汽车");
        vehicle.put("epaddress", "北京市");
        vehicle.put("epcode", "123456789123456789");
        vehicleList.add(vehicle);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/receiveSaleVehicle", vehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBattery(){
        Map<String,Object> cell1=new HashMap<>();
        cell1.put("code","C211");
        cell1.put("type","C");
        List<Map<String,Object>> cellList1=new ArrayList<>();
        cellList1.add(cell1);
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M210");
        module1.put("childCodeList",cellList1);
        module1.put("modelId","zhangM");
        module1.put("type","M");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P200");
        pack1.put("childCodeList",moduleList1);
        pack1.put("modelId","zhangP");
        pack1.put("type","P");
        pack1.put("orderCode","R001");
        List<Map<String,Object>> packList=new ArrayList<>();
        packList.add(pack1);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveReplaceBattery", packList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceRecord(){
        List<Map<String,Object>> recordList=new ArrayList<>();
        Map<String,Object> record=new HashMap<>();
        record.put("vin", "LNBSCB3F0DD130955");
        record.put("replaceDate", "2018-01-05");
        record.put("oldCode", "P100");
        record.put("whereaboutsId", "123456789123456789");
        record.put("whereaboutsName", "维修厂");
        record.put("newCode", "P200");
        record.put("batterySpecies", "P");
        recordList.add(record);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveReplaceRecord", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createFactoryRepair(){
        Map<String,Object> cell1=new HashMap<>();
        cell1.put("code","C311");
        cell1.put("type","C");
        List<Map<String,Object>> cellList1=new ArrayList<>();
        cellList1.add(cell1);
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M310");
        module1.put("childCodeList",cellList1);
        module1.put("modelId","zhangM");
        module1.put("type","M");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P300");
        pack1.put("childCodeList",moduleList1);
        pack1.put("modelId","zhangP");
        pack1.put("type","P");
        pack1.put("orderCode","R001");
        List<Map<String,Object>> repairList=new ArrayList<>();
        Map<String,Object> repair=new HashMap<>();
        repair.put("vin", "LNBSCB3F0DD130955");
        repair.put("replaceDate", "2018-01-05");
        repair.put("oldCode", "P200");
        repair.put("whereaboutsId", "123456789123456789");
        repair.put("whereaboutsName", "维修厂");
        repair.put("newCodeBean", pack1);
        repair.put("batterySpecies", "P");
        repairList.add(repair);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveReplaceOld", repairList, factory_token, factory_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createRecoverStorage(){
        List<Map<String,Object>> recoverStorageList=new ArrayList<>();
        Map<String,Object> recoverStorage=new HashMap<>();
        recoverStorage.put("storageDate", "2018-01-05");
        recoverStorage.put("recoverUnitCode", "123456789123456789");
        recoverStorage.put("recoverUnitName", "维修厂");
        recoverStorage.put("code", "P400");
        recoverStorage.put("batterySpecies", "P");
        recoverStorageList.add(recoverStorage);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/recover/receiveRecoverStorage", recoverStorageList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRetireVehicle(){
        List<Map<String,Object>> retireVehicleList=new ArrayList<>();
        Map<String,Object> retireVehicle=new HashMap<>();
        retireVehicle.put("code", "P400");
        retireVehicle.put("whereaboutsCode", "123456789123412341");
        retireVehicle.put("whereaboutsName", "维修厂");
        retireVehicle.put("retireDate", "2018-01-05");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "5");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "2.5");
        retireVehicleList.add(retireVehicle);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveRetiredOldVehicle", retireVehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceBatteryInStorage(){
        List<String> cellList1=new ArrayList<>();
        cellList1.add("C511");
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M510");
        module1.put("cellList",cellList1);
        module1.put("cellCodeSize","1");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P500");
        pack1.put("moduleList",moduleList1);
        pack1.put("replaceUnitCode","123456789123412342");
        pack1.put("replaceUnitName","换电厂");

        List<String> cellList2=new ArrayList<>();
        cellList2.add("C611");
        Map<String,Object> module2=new HashMap<>();
        module2.put("code","M610");
        module2.put("cellList",cellList2);
        module2.put("cellCodeSize","1");
        List<Map<String,Object>> moduleList2=new ArrayList<>();
        moduleList2.add(module2);
        Map<String,Object> pack2=new HashMap<>();
        pack2.put("code","P600");
        pack2.put("moduleList",moduleList2);
        pack2.put("replaceUnitCode","123456789123412342");
        pack2.put("replaceUnitName","换电厂");

        List<Map<String,Object>> packList=new ArrayList<>();
        packList.add(pack1);
        packList.add(pack2);

        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/replaceBattery/batteryInStorage", packList, vehicle_token, vehicle_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceReplacement(){
        List<Map<String,Object>> recordList=new ArrayList<>();
        Map<String,Object> record=new HashMap<>();
        record.put("vin", "LNBSCB3F0DD130955");
        record.put("oldCode", "P500");
        record.put("newCode", "P600");
        record.put("replaceUnitCode","123456789123412342");
        record.put("replaceUnitName","换电厂");
        record.put("replaceDate", "2018-01-05");
        recordList.add(record);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/replaceBattery/replacementBattery", recordList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createReplaceRepair(){
        Map<String,Object> cell1=new HashMap<>();
        cell1.put("code","C711");
        cell1.put("type","C");
        List<Map<String,Object>> cellList1=new ArrayList<>();
        cellList1.add(cell1);
        Map<String,Object> module1=new HashMap<>();
        module1.put("code","M710");
        module1.put("childCodeList",cellList1);
        module1.put("type","M");
        List<Map<String,Object>> moduleList1=new ArrayList<>();
        moduleList1.add(module1);
        Map<String,Object> pack1=new HashMap<>();
        pack1.put("code","P700");
        pack1.put("childCodeList",moduleList1);
        pack1.put("type","P");
        List<Map<String,Object>> repairList=new ArrayList<>();
        Map<String,Object> repair=new HashMap<>();
        repair.put("vin", "LNBSCB3F0DD130955");
        repair.put("repairDate", "2018-01-05");
        repair.put("oldCode", "P600");
        repair.put("newCode", "P700");
        repair.put("newCodeBean", pack1);
        repair.put("retireUnitCode", "123456789123412341");
        repair.put("retireUnitName", "维修厂");
        repair.put("whereaboutsCode", "123456789123412342");
        repair.put("whereaboutsName", "出库厂");
        repairList.add(repair);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/replaceBattery/replaceRepairBattery", repairList, vehicle_token, vehicle_key);
        String result = parsToMap(response, factory_key);
        System.out.println(result);
    }

    public static void createReplaceRetire(){
        List<Map<String,Object>> retireVehicleList=new ArrayList<>();
        Map<String,Object> retireVehicle=new HashMap<>();
        retireVehicle.put("code", "P500");
        retireVehicle.put("whereaboutsCode", "123456789123456789");
        retireVehicle.put("whereaboutsName", "电池厂");
        retireVehicle.put("retireDate", "2018-01-05");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "4");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "2.5");
        retireVehicleList.add(retireVehicle);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveRetiredOldReplace", retireVehicleList, vehicle_token, vehicle_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static void createRetireFactory(){
        List<Map<String,Object>> retireVehicleList=new ArrayList<>();
        Map<String,Object> retireVehicle=new HashMap<>();
        retireVehicle.put("code", "P200");
        retireVehicle.put("whereaboutsCode", "123456789123456789");
        retireVehicle.put("whereaboutsName", "电池厂");
        retireVehicle.put("retireDate", "2018-01-05");
        retireVehicle.put("batterySpecies", "P");
        retireVehicle.put("retireUnitCode", "123456789123123123");
        retireVehicle.put("retireUnitName", "退役厂商");
        retireVehicle.put("factoryType", "2");
        retireVehicle.put("batteryType", "A");
        retireVehicle.put("batteryWeight", "2.5");
        retireVehicleList.add(retireVehicle);
        HttpResponse response = send(ip1+"/bitnei/v1.0/battery/sail/receiveRetiredOld", retireVehicleList, factory_token, factory_key);
        String result = parsToMap(response, vehicle_key);
        System.out.println(result);
    }

    public static HttpResponse send(String url, Object data, String token, String key){
        try {
            Map<String, Object> requestMap = new HashMap<String, Object>();
            String requestMsg = new JsonSerializer().deep(true).serialize(data);
            String enStr = encrypt(requestMsg, key);
            requestMap.put("requestMsg", enStr);
            requestMap.put("timestamp", System.currentTimeMillis());
            String sign = byteArrayToHexString(encryptHMAC(enStr.getBytes(), key));
            requestMap.put("sign", sign);
            HttpResponse response = getRequest(url, token, requestMap).send().unzip().unzip();
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String parsToMap( HttpResponse response,String key){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(response.body(), Map.class);
            String result="";
            if(isNotEmpty(map.get("data"))){
                result = decrypt(map.get("data").toString(), key);
            }else {
                result = decrypt(map.get("msg").toString(), key);
            }
            return "code:"+map.get("code")+""+result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String content, String password) {
        try {
            if(StringUtils.isEmpty(content)) {
                return "";
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            BASE64Encoder coder = new BASE64Encoder();
            coder.encode(enCodeFormat);
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(byteContent);
            String str = Base64.encode(result);
            return str;
        } catch (NoSuchAlgorithmException var13) {
            ;
        } catch (NoSuchPaddingException var14) {
            ;
        } catch (InvalidKeyException var15) {
            ;
        } catch (UnsupportedEncodingException var16) {
            ;
        } catch (IllegalBlockSizeException var17) {
            ;
        } catch (BadPaddingException var18) {
            ;
        }
        return null;
    }

    public static boolean isNotEmpty(Object o) {
        if (o == null) {
            return false;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return false;
        } else {
            return true;
        }
    }

    public static String FilterNull(Object o) {
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "" ;
    }

    public static String decrypt(String str, String password) {
        try {
            byte[] content = Base64.decode(str);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            byte[] result = cipher.doFinal(content);
            return new String(result, "UTF-8");
        } catch (NoSuchAlgorithmException var10) {
            ;
        } catch (NoSuchPaddingException var11) {
            ;
        } catch (InvalidKeyException var12) {
            ;
        } catch (IllegalBlockSizeException var13) {
            ;
        } catch (BadPaddingException var14) {
            ;
        } catch (Exception var15) {
            ;
        }
        return "";
    }

    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        byte[] var2 = bytes;
        int var3 = bytes.length;
        for(int var4 = 0; var4 < var3; ++var4) {
            byte aByte = var2[var4];
            int v = aByte & 255;
            if(v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static HttpRequest getRequest(String url, String accessToken, Map<String, Object> params) {
        HttpRequest request = HttpRequest.post(url);
        request.header("Authorization", "Bearer " + accessToken);
        request.header("Content-Type", "application/json; charset=utf-8");
        request.acceptEncoding("gzip");
        request.body((new JsonSerializer()).serialize(params));
        return request;
    }

    public static void test(){
        try {
            String data="{\"systemId\":\"1111333\",\"code\":\"031PE0032X05AA85N0300123\",\"moduleList\":[{\"cellModelId\":\"SEPNi8688190-15Ah\",\"code\":\"031ME00F2X05AA85K0301261\",\"modelId\":\"8688190-4P\",\"cellList\":[\"031CE05008523A8530101744\",\"031CE05008523A8530103043\",\"031CE05008524A8520502468\",\"031CE05008524A8530504838\"]},{\"cellModelId\":\"SEPNi8688190-15Ah\",\"code\":\"031ME00F2X05AA85K0301264\",\"modelId\":\"8688190-4P\",\"cellList\":[\"031CE05008524A8530505037\",\"031CE05008523A8530103945\",\"031CE05008523A8530101713\",\"031CE05008523A8530103008\"]},{\"cellModelId\":\"SEPNi8688190-15Ah\",\"code\":\"031ME00F2X05AA85K0301265\",\"modelId\":\"8688190-4P\",\"cellList\":[\"031CE05008524A8520200205\",\"031CE05008524A8520501236\",\"031CE05008524A8530302959\",\"031CE05008524A8530300984\"]},{\"cellModelId\":\"SEPNi8688190-15Ah\",\"code\":\"031ME00F2X05AA85K0301262\",\"modelId\":\"8688190-4P\",\"cellList\":[\"031CE05008524A8530502054\",\"031CE05008523A8530102049\",\"031CE05008524A8530504205\",\"031CE05008524A8530504600\"]},{\"cellModelId\":\"SEPNi8688190-15Ah\",\"code\":\"031ME00F2X05AA85K0301266\",\"modelId\":\"8688190-4P\",\"cellList\":[\"031CE05008524A8530504636\",\"031CE05008524A84Y0205616\",\"031CE05008524A8530300968\",\"031CE05008523A8530102470\"]},{\"cellModelId\":\"SEPNi8688190-15Ah\",\"code\":\"031ME00F2X05AA85K0301263\",\"modelId\":\"8688190-4P\",\"cellList\":[\"031CE05008523A8530102045\",\"031CE05008524A8530301326\",\"031CE05008524A8530504618\",\"031CE05008524A8530502019\"]},{\"cellModelId\":\"SEPNi8688190-15Ah\",\"code\":\"031ME00F2X05AA85K0301267\",\"modelId\":\"8688190-4P\",\"cellList\":[\"031CE05008523A8530102343\",\"031CE05008524A84W0602111\",\"031CE05008524A8530504638\",\"031CE05008523A8530102345\"]}],\"systemModelId\":\"STJ-HSD-H01-45.68-1\",\"serial\":\"ffff111\",\"modelId\":\"STJ-HSD-H01-45.68-1\",\"orderCode\":\"51000009\"}";
            String url=ip1+"/bitnei/v1.0/battery/vehicle/receiveBatteryProduce";
            String token=vehicle_token;
            String key=vehicle_key;
            Map<String, Object> requestMap = new HashMap<String, Object>();
            String enStr = encrypt(data, key);
            requestMap.put("requestMsg", enStr);
            requestMap.put("timestamp", System.currentTimeMillis());
            String sign = byteArrayToHexString(encryptHMAC(enStr.getBytes(), key));
            requestMap.put("sign", sign);
            HttpResponse response = getRequest(url, token, requestMap).send().unzip().unzip();
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(response.body(), Map.class);
            String result="";
            if(isNotEmpty(map.get("data"))){
                result = decrypt(map.get("data").toString(), key);
            }else {
                result = decrypt(map.get("msg").toString(), key);
            }
            System.out.println(map.get("code")+""+result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}