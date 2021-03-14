package com.pethoalpar.converters;

import java.util.List;

import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.engine.backend.document.IndexObjectFieldReference;
import org.hibernate.search.engine.backend.document.model.dsl.IndexSchemaElement;
import org.hibernate.search.engine.backend.document.model.dsl.IndexSchemaObjectField;
import org.hibernate.search.mapper.pojo.bridge.PropertyBridge;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.PropertyBinder;
import org.hibernate.search.mapper.pojo.bridge.binding.PropertyBindingContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.PropertyBridgeWriteContext;

import com.pethoalpar.entity.AnimalProperty;

/**
 * @author alpar.petho
 *
 */
public class AnimalPropertyBinder implements PropertyBinder {
	
	@Override
	public void bind(PropertyBindingContext context) {
		context.dependencies().use("propertyName").use("propertyValue");

		IndexSchemaElement schemaElement = context.indexSchemaElement();

		IndexSchemaObjectField userMetadataField = schemaElement.objectField("properties");

		userMetadataField.fieldTemplate("userMetadataValueTemplate", f -> f.asString().analyzer("english"));

		context.bridge(List.class, new UserMetadataBridge(userMetadataField.toReference()));

	}

	@SuppressWarnings("rawtypes")
	private static class UserMetadataBridge implements PropertyBridge<List> {

		private final IndexObjectFieldReference userMetadataFieldReference;

		private UserMetadataBridge(IndexObjectFieldReference userMetadataFieldReference) {
			this.userMetadataFieldReference = userMetadataFieldReference;
		}

		@Override
		public void write(DocumentElement target, List bridgedElement, PropertyBridgeWriteContext context) {
			@SuppressWarnings("unchecked")
			List<AnimalProperty> userMetadata = bridgedElement;

			DocumentElement indexedUserMetadata = target.addObject(userMetadataFieldReference);

			for (AnimalProperty property : userMetadata) {
				indexedUserMetadata.addValue(property.getPropertyName(), property.getPropertyValue());
			}
		}
	}

}
