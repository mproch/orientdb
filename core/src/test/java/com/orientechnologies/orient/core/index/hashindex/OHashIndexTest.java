package com.orientechnologies.orient.core.index.hashindex;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.index.OSimpleKeyIndexDefinition;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;

/**
 * @author <a href="mailto:enisher@gmail.com">Artem Orobets</a>
 */
@Test
public class OHashIndexTest {
  private ODatabaseDocumentTx db;

  @BeforeClass
  public void setUp() throws Exception {

    db = new ODatabaseDocumentTx("local:target/hashIndexTest");

    if (db.exists()) {
      db.open("admin", "admin");
      db.drop();
    }

    db.create();
  }

  @AfterClass
  public void tearDown() throws Exception {
    db.close();
  }

  @Test(enabled = false)
  public void testCreateAutomaticHashIndex() throws Exception {
    final OClass oClass = db.getMetadata().getSchema().createClass("testClass");
    oClass.createProperty("name", OType.STRING);
    final OIndex<?> index = oClass.createIndex("testClassNameIndex", OClass.INDEX_TYPE.UNIQUE_HASH, "name");

    Assert.assertNotNull(index);
  }

  @Test
  public void testCreateManualHashIndex() throws Exception {
    final OIndex<?> index = db
        .getMetadata()
        .getIndexManager()
        .createIndex("manualHashIndex", OClass.INDEX_TYPE.UNIQUE_HASH.toString(), new OSimpleKeyIndexDefinition(OType.STRING),
            null, null);

    Assert.assertNotNull(index);
  }
}
