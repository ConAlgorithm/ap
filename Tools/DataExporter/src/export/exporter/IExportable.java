package export.exporter;

public interface IExportable<T> {
	public void export(String appId, T record);
}
