package com.noob.mediodecode;

import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * Created by xiaoqi on 2018/1/5.
 */

public class AudioPlayer {
	private int mFrequency;// 采样率
	private int mChannel;// 声道
	private int mSampBit;// 采样精度
	private AudioTrack mAudioTrack;
// AudioPlayer{mFrequency=44100, mChannel=12, mSampBit=2, mAudioTrack=android.media.AudioTrack@51d364f}/*//*/*****************
	public AudioPlayer(int frequency, int channel, int sampbit) {
		this.mFrequency = frequency;
		this.mChannel = channel;
		this.mSampBit = sampbit;
	}

	/**
	 * 初始化
	 */
	public void init() {
		if (mAudioTrack != null) {
			release();
		}
		// 获得构建对象的最小缓冲区大小
		int minBufSize = AudioTrack.getMinBufferSize(mFrequency, mChannel, mSampBit);
		//音频播放的方式，有两种方式 MODE_STATIC 或者 MODE_STREAM
		mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, mFrequency, mChannel, mSampBit, minBufSize, AudioTrack.MODE_STREAM);
		mAudioTrack.play();
	}

	/**
	 * 释放资源
	 */
	private void release() {
		if (mAudioTrack != null) {
			mAudioTrack.stop();
			mAudioTrack.release();
		}
	}

	/**
	 * 将解码后的pcm数据写入audioTrack播放
	 *
	 * @param data   数据
	 * @param offset 偏移
	 * @param length 需要播放的长度
	 */
	public void play(byte[] data, int offset, int length) {
		if (data == null || data.length == 0) {
			return;
		}
		try {
			mAudioTrack.write(data, offset, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "AudioPlayer{" +
				"mFrequency=" + mFrequency +
				", mChannel=" + mChannel +
				", mSampBit=" + mSampBit +
				", mAudioTrack=" + mAudioTrack +
				'}';
	}
}
