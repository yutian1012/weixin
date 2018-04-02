package org.ipph.app.weixin.convert;

public interface IConvert<S,T> {
	T convert(S source);
}
