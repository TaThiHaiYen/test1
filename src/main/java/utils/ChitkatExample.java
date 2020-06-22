package utils;

import com.chilkatsoft.CkGlobal;
import com.chilkatsoft.CkScp;
import com.chilkatsoft.CkSsh;

public class ChitkatExample {
	static {
		try {
			// copy file chilkat.dll vào thư mục project
			System.loadLibrary("chilkat");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}

	public void getTrial() {
		CkGlobal glob = new CkGlobal();
		boolean success = glob.UnlockBundle("Anything for 30-day trial");
		if (success != true) {
			System.out.println(glob.lastErrorText());
			return;
		}
		int status = glob.get_UnlockStatus();
		if (status == 2) {
			System.out.println("Unlocked using purchased unlock code.");
		} else {
			System.out.println("Uncloked in trail mode.");
		}
		System.out.println(glob.lastErrorText());
	}

	public static void main(String argv[]) {
		// This example requires the Chilkat API to have been previously unlocked.
		// See Global Unlock Sample for sample code.

		CkSsh ssh = new CkSsh();
		ChitkatExample gg = new ChitkatExample();
		gg.getTrial();
		String hostname = "drive.ecepvn.org";
		int port = 2227;

		// Connect to an SSH server:
		boolean success = ssh.Connect(hostname, port);
		if (success != true) {
			System.out.println(ssh.lastErrorText());
			return;
		}

		// Wait a max of 5 seconds when reading responses..
		ssh.put_IdleTimeoutMs(5000);

		// Authenticate using login/password:
		success = ssh.AuthenticatePw("guest_access", "123456");
		if (success != true) {
			System.out.println(ssh.lastErrorText());
			return;
		}

		// Once the SSH object is connected and authenticated, we use it
		// in our SCP object.
		CkScp scp = new CkScp();

		success = scp.UseSsh(ssh);
		if (success != true) {
			System.out.println(scp.lastErrorText());
			return;
		}
		// down tất cả file bắt đầu bằng sinhvien

		scp.put_SyncMustMatch("sinhvien*.*");
		String remotePath = "/volume1/ECEP/song.nguyen/DW_2020/data";

		String localPath = "D:\\scp";// Thư mục muốn down file về
		success = scp.SyncTreeDownload(remotePath, localPath, 2, false);

		if (success != true) {
			System.out.println(scp.lastErrorText());
			return;
		}

		System.out.println("SCP download file success!!!");

		// Disconnect
		ssh.Disconnect();
	}
}
// Đếm bao nhiêu row
//import lấy data về local
// 1 .kt trong log file down về có(thành công) trong hệ thống chưa(import cái ms hoặc bị lỗi)
//