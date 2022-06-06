package com.stschools.payment.vnpay;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payVn")
public class VnPayController {

//	public static Map<String, VeRequest> listSlotVnPay = new HashMap<String, VeRequest>();
//
//	@Autowired
//	private EmailService2 emailser;
//
//	@Autowired
//	LinksGereneral linksG;
//
//	public static String linkURL = "";
//
//	@RequestMapping(value = "/submit", method = RequestMethod.GET)
//	public String post(@RequestParam(name = "vnp_Amount", required = false, defaultValue = "null") Double vnp_Amount,
//			@RequestParam(name = "vnp_BankCode", required = false, defaultValue = "null") String vnp_BankCode,
//			@RequestParam(name = "vnp_BankTranNo", required = false, defaultValue = "null") String vnp_BankTranNo,
//			@RequestParam(name = "vnp_CardType", required = false, defaultValue = "null") String vnp_CardType,
//			@RequestParam(name = "vnp_OrderInfo", required = false, defaultValue = "null") String vnp_OrderInfo,
//			@RequestParam(name = "vnp_PayDate", required = false, defaultValue = "null") String vnp_PayDate,
//			@RequestParam(name = "vnp_ResponseCode", required = false, defaultValue = "null") String vnp_ResponseCode,
//			@RequestParam(name = "vnp_TmnCode", required = false, defaultValue = "null") String vnp_TmnCode,
//			@RequestParam(name = "vnp_TransactionNo", required = false, defaultValue = "null") String vnp_TransactionNo,
//			@RequestParam(name = "vnp_TxnRef", required = false, defaultValue = "null") String vnp_TxnRef,
//			@RequestParam(name = "vnp_SecureHashType", required = false, defaultValue = "null") String vnp_SecureHashType,
//			@RequestParam(name = "vnp_SecureHash", required = false, defaultValue = "null") String vnp_SecureHash,
//			@RequestParam(name = "sdt", required = false, defaultValue = "null") String sdt,
//			@RequestParam(name = "email", required = false, defaultValue = "null") String email,
//			@RequestParam(name = "date", required = false, defaultValue = "null") String date,
//			@RequestParam(name = "code_slot1", required = false, defaultValue = "null") String code_slot1,
//			@RequestParam(name = "code_slot2", required = false, defaultValue = "null") String code_slot2)
//			throws UnsupportedEncodingException {
//
//		String button = "<a href=\r\n" + linkURL + ">\r\n"
//				+ "<button style=\"vertical-align:middle;position: relative;display: inline-block;\r\n"
//				+ "  border-radius: 4px;\r\n" + "  background-color: #f4511e;\r\n" + "  border: none;\r\n"
//				+ "  color: #FFFFFF;\r\n" + "  text-align: center;\r\n" + "  font-size: 22px;\r\n"
//				+ "  padding: 20px;\r\n" + "  width:40%;\r\n" + "  transition: all 0.5s;\r\n" + "  cursor: pointer;\r\n"
//				+ "  margin-left: 30%;\">" + "OK" + "</button>" + "</a>";
//		String responses = "<div><h1 style=\"text-align: center;\">THANH TOÁN THÀNH CÔNG</h1></div>" + button;
//		Long messageSQL1 = 0L;
//		Long message1 = 0L;
//		VeRequest valueOfCodeSlot = VnPayController.listSlotVnPay.get(code_slot1);
//		// Sercond ticket
//		if (!code_slot2.equals("isNull")) {
//			VeRequest valueOfCodeSlot2 = listSlotVnPay.get(code_slot2);
//			// phép màu
//			SlotRequest demo1 = valueOfCodeSlot.getSlot().get(0);
//			for (SlotRequest x : valueOfCodeSlot2.getSlot()) {
//				x.setTen(demo1.getTen());
//			}
//			//
//			String code2 = GenerateCode.generateStringToEmail(new String(""));
//			String slots2 = UtilsService.convertListObjectToJsonArrayt(valueOfCodeSlot2.getSlot());
//			List<String> slotMail2 = new ArrayList<String>();
//			for (int i = 0; i < valueOfCodeSlot2.getSlot().size(); i++) {
//				String g = "";
//				if (valueOfCodeSlot2.getSlot().get(i).getSoGhe() <= 22) {
//					if (valueOfCodeSlot2.getSlot().get(i).getSoGhe() < 10) {
//						g = "A0" + String.valueOf(valueOfCodeSlot2.getSlot().get(i).getSoGhe());
//					} else {
//						g = "A" + String.valueOf(valueOfCodeSlot2.getSlot().get(i).getSoGhe());
//					}
//				} else {
//					if (valueOfCodeSlot2.getSlot().get(i).getSoGhe() < 32) {
//						g = "B0" + String.valueOf(valueOfCodeSlot2.getSlot().get(i).getSoGhe() - 22);
//					} else {
//						g = "B" + String.valueOf(valueOfCodeSlot2.getSlot().get(i).getSoGhe() - 22);
//					}
//				}
//				slotMail2.add(g);
//			}
//			String slotMails2 = slotMail2.stream().collect(Collectors.joining(String.valueOf(",")));
//			String tenTuyen2 = veDao.spGetNameTuyenXe(valueOfCodeSlot2.getIdTuyenXe());
//			String ngay2 = UtilsService.changeDateToString(UtilsService.changeStringToDate(valueOfCodeSlot2.getDate()));
//			// mail
//			try {
//				 sendMail(email, code2, tenTuyen2, valueOfCodeSlot2.getGioChay(), slotMails2,
//				 valueOfCodeSlot2.getGiaVe(), ngay2, 1, "");
//			} catch (Exception e) {
//				System.out.println("chem3ee");
//			}
//			//
//			Double parde2 = valueOfCodeSlot2.getGiaVe() / 4000;
//			int point2 = parde2.intValue();
//			message1 = pointDao.spCreateHistoryPoint(email, point2, 0);
//			System.out.print(message1);
//			// tạo model để insert
//
//			valueOfCodeSlot2.setSdt(sdt);
//			valueOfCodeSlot2.setEmail(email);
//			valueOfCodeSlot2.setPaypalId("isNull");
//			valueOfCodeSlot2.setZaloPayId("isNull");
//			valueOfCodeSlot2.setVnpayId(vnp_TransactionNo);
//			// create Journeys
//			Journeys jo2 = new Journeys();
//			jo2.setRouter_name(tenTuyen2);
//			jo2.setRun_date(UtilsService.changeStringToDate(valueOfCodeSlot2.getDate()));
//			jo2.setStatus(1);
//			jo2.setDeparture_time(valueOfCodeSlot2.getGioChay());
//			insertJourneys(jo2, valueOfCodeSlot2.getIdTuyenXe());
//			//
//			messageSQL1 = veDao.create(valueOfCodeSlot2, slots2, code2);
//
//			System.out.print(messageSQL1);
//		}
//		// fisrt ticket
//		SlotRequest demo1 = valueOfCodeSlot.getSlot().get(0);
//		for (SlotRequest x : valueOfCodeSlot.getSlot()) {
//			x.setTen(demo1.getTen());
//		}
//		String code = GenerateCode.generateStringToEmail(new String(""));
//		String slots = UtilsService.convertListObjectToJsonArrayt(valueOfCodeSlot.getSlot());
//		List<String> slotMail = new ArrayList<String>();
//		for (int i = 0; i < valueOfCodeSlot.getSlot().size(); i++) {
//			String g = "";
//			if (valueOfCodeSlot.getSlot().get(i).getSoGhe() <= 22) {
//				if (valueOfCodeSlot.getSlot().get(i).getSoGhe() < 10) {
//					g = "A0" + String.valueOf(valueOfCodeSlot.getSlot().get(i).getSoGhe());
//				} else {
//					g = "A" + String.valueOf(valueOfCodeSlot.getSlot().get(i).getSoGhe());
//				}
//			} else {
//				if (valueOfCodeSlot.getSlot().get(i).getSoGhe() < 32) {
//					g = "B0" + String.valueOf(valueOfCodeSlot.getSlot().get(i).getSoGhe() - 22);
//				} else {
//					g = "B" + String.valueOf(valueOfCodeSlot.getSlot().get(i).getSoGhe() - 22);
//				}
//			}
//			slotMail.add(g);
//		}
//		String slotMails = slotMail.stream().collect(Collectors.joining(String.valueOf(",")));
//		String tenTuyen = veDao.spGetNameTuyenXe(valueOfCodeSlot.getIdTuyenXe());
//		String ngay = UtilsService.changeDateToString(UtilsService.changeStringToDate(valueOfCodeSlot.getDate()));
//		// mail
//		try {
//			 sendMail(email, code, tenTuyen, valueOfCodeSlot.getGioChay(), slotMails,
//			 valueOfCodeSlot.getGiaVe(), ngay, 1, "");
//		} catch (Exception e) {
//			System.out.println("chem3ee");
//		}
//		Double parde = valueOfCodeSlot.getGiaVe() / 4000;
//		int point = parde.intValue();
//		Long message2 = pointDao.spCreateHistoryPoint(email, point, 0);
//		System.out.print(message1);
//		// tạo model để insert
//		valueOfCodeSlot.setSdt(sdt);
//		valueOfCodeSlot.setEmail(email);
//		valueOfCodeSlot.setPaypalId("isNull");
//		valueOfCodeSlot.setZaloPayId("isNull");
//		valueOfCodeSlot.setVnpayId(vnp_TransactionNo);
//		// create Journeys
//		Journeys jo = new Journeys();
//		jo.setRouter_name(tenTuyen);
//		jo.setRun_date(UtilsService.changeStringToDate(valueOfCodeSlot.getDate()));
//		jo.setStatus(1);
//		jo.setDeparture_time(valueOfCodeSlot.getGioChay());
//		insertJourneys(jo, valueOfCodeSlot.getIdTuyenXe());
//		//
//		Long messageSQL2 = veDao.create(valueOfCodeSlot, slots, code);
//		System.out.print(messageSQL2);
//		if (messageSQL1 == 1 || message1 == 1 || message2 == 1 || messageSQL2 == 1) {
//			responses = "<div><h1 style=\" text-align: center;	\">THANH TOÁN THẤT BẠI</h1></div>" + button;
//		} else {
//			responses = "<div><h1 style=\" text-align: center;	\">THANH TOÁN THÀNH CÔNG</h1></div>" + button;
//		}
//		return responses;
//	}
//
//	@RequestMapping(value = "/get-code", method = RequestMethod.POST)
//	public ResponseEntity<BaseResponse> createStringQuery(
//			@RequestParam(name = "link", required = false, defaultValue = "null") String link,
//			@RequestParam(name = "vnp_IpAddr", required = false, defaultValue = "null") String vnp_IpAddr,
//			@RequestParam(name = "vnp_OrderInfo", required = false, defaultValue = "null") String vnp_OrderInfo,
//			@RequestParam(name = "vnp_OrderType", required = false, defaultValue = "null") String vnp_OrderType,
//			@RequestBody VeHoiKhuRequest wrapper) throws UnsupportedEncodingException {
//		BaseResponse response = new BaseResponse();
//		VeRequest wrapperTemp1 = new VeRequest();
//		wrapperTemp1.setSlot(wrapper.getSlot());
//		VnPayController.linkURL = link;
//		System.out.print(linkURL);
//		String vnp_Version = "2";
//		String vnp_Command = "pay";
//		String vnp_TmnCode = "BZKHFB2U";
//		String vnp_BankCode = "NCB";
//		String vnp_CreateDate;
//		String vnp_CurrCode = "VND";
//		String vnp_Locale = "vn";
//		String vnp_TxnRef = "HoaLeTiesss3";
//		String vnp_SupChecksumt = "DVDGIHLTSKCAWWBRXVRKMJXTFDJHOQCP";
//		wrapperTemp1.setGiaVe(wrapper.getGiaVe());
//		wrapperTemp1.setDate(wrapper.getDate());
//		wrapperTemp1.setIdTuyenXe(wrapper.getIdTuyenXe());
//		wrapperTemp1.setGioKetThuc(wrapper.getGioKetThuc());
//		wrapperTemp1.setGioChay(wrapper.getGioChay());
//		wrapperTemp1.setSlot(wrapper.getSlot());
//		wrapperTemp1.setDiemXuong(wrapper.getDiemXuong());
//		Double vnp_Amount = wrapper.getGiaVe();
//		Date dt = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//		String dateString = formatter.format(dt);
//		vnp_CreateDate = dateString;
//		String codeSlot1 = dateString + "code1";
//		listSlotVnPay.put(codeSlot1, wrapperTemp1);
//		String codeSlot2 = "isNull";
//		String vnp_ReturnUrl = "";
//
//		VeRequest wrapperTemp = new VeRequest();
//		wrapperTemp.setGiaVe(wrapper.getGiaVe2());
//		wrapperTemp.setDate(wrapper.getDate2());
//		wrapperTemp.setIdTuyenXe(wrapper.getIdTuyenXe2());
//		wrapperTemp.setGioKetThuc(wrapper.getGioKetThuc2());
//		wrapperTemp.setGioChay(wrapper.getGioChay2());
//		wrapperTemp.setSlot(wrapper.getSlot2());
//		wrapperTemp.setDiemXuong(wrapper.getDiemXuong2());
//		// Nếu là vé 2 chiều
//		if (wrapper.getGioChay2() != null) {
//			vnp_Amount = wrapper.getGiaVe() + wrapper.getGiaVe2();
//			codeSlot2 = formatter.format(new Date()) + "code2";
//			listSlotVnPay.put(codeSlot2, wrapperTemp);
//		} else {
//
//		}
//		vnp_Amount *= 100;
//		vnp_ReturnUrl = linksG.getLinksUrlApp() + "api/payVn/submit?" + "&sdt=" + wrapper.getSdt().toString()
//				+ "&email=" + wrapper.getEmail().toString() + "&code_slot1=" + codeSlot1.toString() + "&code_slot2="
//				+ codeSlot2.toString();
//		vnp_TxnRef = vnp_CreateDate;
//		double vnp_Amountd = vnp_Amount;
//		Integer vnp_Amounti = (int) vnp_Amountd;
//
//		// Tạo mã map links
//
//		Map<String, String> vnp_Params = new HashMap<>();
//		vnp_Params.put("vnp_Version", vnp_Version);
//		vnp_Params.put("vnp_Command", vnp_Command);
//		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
//		vnp_Params.put("vnp_Amount", vnp_Amounti.toString());
//		vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
//		vnp_Params.put("vnp_BankCode", vnp_BankCode);
//		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
//		vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
//		vnp_Params.put("vnp_OrderType", vnp_OrderType);
//		vnp_Params.put("vnp_Locale", vnp_Locale);
//		vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
//		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
//		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
//		String queryUrl = VnPayConfig.generateQueryUrl(vnp_Params, vnp_SupChecksumt);
//		response.setData(queryUrl);
//		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/refund", method = RequestMethod.POST)
//	public ResponseEntity<BaseResponse> refundVnpay(
//
//	) throws UnsupportedEncodingException {
//		BaseResponse response = new BaseResponse();
//
//		String vnp_Command = "pay";
//		String vnp_TmnCode = "BZKHFB2U";
//		String vnp_TransactionNo = "13494893";
//		Double vnp_Amount = 200000.0;
//		Date dt = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//		String dateString = formatter.format(dt);
//		String vnp_TransDate = dateString;
//
//		Map<String, String> vnp_Params = new HashMap<>();
//		vnp_Params.put("vnp_Command", vnp_Command);
//		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
//		vnp_Params.put("vnp_TransactionNo", vnp_TransactionNo);
//		vnp_Params.put("vnp_TransDate", vnp_TransDate);
//		vnp_Params.put("vnp_Amount", vnp_Amount + "000");
//
//		String queryUrl = "";
//
//		response.setData(queryUrl);
//		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
//	}
//
//	public void insertJourneys(Journeys jo, Integer tuyenId) {
//		Journeys journeys = new Journeys();
//		journeys = journeysDao.getJourneysByNameAndDate(jo.getRouter_name(), jo.getRun_date(), jo.getDeparture_time());
//		if (journeys == null) {
//			Xe xe = xeDao.getXeByTuyenXeAndHour(tuyenId, UtilsService.changeHourSToHourD(jo.getDeparture_time()));
//			User use = khachHangDao.getById(xe.getDriverId());
//
//			jo.setDriver_name(use.getTenKh());
//			jo.setPhone(use.getSdt());
//			jo.setBus_code(xe.getBusCode());
//			jo.setDrive_id(xe.getDriverId());
//			jo.setRouter_id(tuyenId);
//			jo.setOld_driver_name("");
//			jo.setOld_driver_phone("");
//			jo.setPlace_accident("");
//			jo.setReason("");
//			jo.setReason_delay("");
//			jo.setTime_accident("");
//			jo.setTime_delay("");
//			journeysDao.create(jo);
//		}
//	}
//
//	public void sendMail(String mail, String code, String tenTuyen, String gioChay, String slotMails, Double giaVe,
//			String ngay, Integer type, String money) {
//		Thread t = new Thread(new MailThread(mail, code, tenTuyen, gioChay, slotMails, giaVe, ngay, type, money));
//		t.start();
//	}
}
