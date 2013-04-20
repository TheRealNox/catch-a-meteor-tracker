package com.nox.catch_a_meteor.layers;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.nox.catch_a_meteor.R;
import com.nox.catch_a_meteor.base.Lists;
import com.nox.catch_a_meteor.base.TimeConstants;
import com.nox.catch_a_meteor.control.AstronomerModel;
import com.nox.catch_a_meteor.dao.DatabaseHelper;
import com.nox.catch_a_meteor.model.MeteorShowerEvent;
import com.nox.catch_a_meteor.model.SpaceObjectObservation;
import com.nox.catch_a_meteor.renderer.RendererObjectManager.UpdateType;
import com.nox.catch_a_meteor.source.AbstractAstronomicalSource;
import com.nox.catch_a_meteor.source.AstronomicalSource;
import com.nox.catch_a_meteor.source.ImageSource;
import com.nox.catch_a_meteor.source.Sources;
import com.nox.catch_a_meteor.source.TextSource;
import com.nox.catch_a_meteor.source.impl.ImageSourceImpl;
import com.nox.catch_a_meteor.source.impl.TextSourceImpl;
import com.nox.catch_a_meteor.units.GeocentricCoordinates;
import com.nox.catch_a_meteor.units.Vector3;

import android.content.res.Resources;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

/**
 * A {@link Layer} to show the user's observed meteors.
 *
 * @author Guillaume Prevost
 */
public class SpaceObjectObservationLayer extends AbstractSourceLayer {
  private List<SpaceObjectObservation> spaceObjectObservations = Lists.newArrayList();

  private final AstronomerModel model;
  private static final int ANY_OLD_YEAR = 100;  // = year 2000
  /** Number of meteors per hour for the larger graphic */
  private static final double METEOR_THRESHOLD_PER_HR = 10;

  public SpaceObjectObservationLayer(AstronomerModel model, Resources resources, List<SpaceObjectObservation> spaceObjectObservations) {
    super(resources, true);
    this.model = model;
    this.spaceObjectObservations = spaceObjectObservations;
  }

  @Override
  protected void initializeAstroSources(ArrayList<AstronomicalSource> sources) {
    for (SpaceObjectObservation spaceObjectObservation : spaceObjectObservations) {
      sources.add(new MeteorRadiantSource(model, spaceObjectObservation, getResources()));
    }
  }

  @Override
  public int getLayerId() {
    return -106;
  }

  @Override
  public String getPreferenceId() {
    return "source_provider.6";
  }

  @Override
  public String getLayerName() {
    return "Meteor observations";
  }

  @Override
  protected int getLayerNameId() {
    return R.string.show_obs_layer_pref;
  }

  private static class MeteorRadiantSource extends AbstractAstronomicalSource {
    private static final int LABEL_COLOR = 0xf67e81;
    private static final Vector3 UP = new Vector3(0.0f, 1.0f, 0.0f);
    private static final long UPDATE_FREQ_MS = 1L * TimeConstants.MILLISECONDS_PER_DAY;
    private static final float SCALE_FACTOR = 0.03f;

    private final List<ImageSource> imageSources = Lists.newArrayList();
    private final List<TextSource> labelSources = Lists.newArrayList();

    private final AstronomerModel model;

    private long lastUpdateTimeMs = 0L;
    private ImageSourceImpl theImage;
    private TextSource label;
    private SpaceObjectObservation spaceObjectObservation;
    private String name;
    private List<String> searchNames = Lists.newArrayList();

    public MeteorRadiantSource(AstronomerModel model, SpaceObjectObservation spaceObjectObservation, Resources resources) {
      this.model = model;
      this.spaceObjectObservation = spaceObjectObservation;
      this.name = spaceObjectObservation.getTitle();

      CharSequence dateObserved = DateFormat.format("MMM dd", spaceObjectObservation.getDateObserved());
      searchNames.add(name + " (" + dateObserved + ")");
      // blank is a 1pxX1px image that should be invisible.
      // We'd prefer not to show any image except on the shower dates, but there
      // appears to be a bug in the renderer/layer interface in that Update values are not
      // respected.  Ditto the label.
      // TODO(johntaylor): fix the bug and remove this blank image
      theImage = new ImageSourceImpl(spaceObjectObservation.getCoordinates(), resources, R.drawable.blank, UP, SCALE_FACTOR);
      imageSources.add(theImage);
      label = new TextSourceImpl(spaceObjectObservation.getCoordinates(), name, LABEL_COLOR);
      labelSources.add(label);
    }

    @Override
    public List<String> getNames() {
      return searchNames;
    }

    @Override
    public GeocentricCoordinates getSearchLocation() {
      return spaceObjectObservation.getCoordinates();
    }

    private void updateSpaceOjectObservation() {
      lastUpdateTimeMs = model.getTime().getTime();

      theImage.setUpVector(UP);
      label.setText(name);
      theImage.setImageId(R.drawable.uranus);
    }

    @Override
    public Sources initialize() {
      updateSpaceOjectObservation();
      return this;
    }

    @Override
    public EnumSet<UpdateType> update() {
      EnumSet<UpdateType> updateTypes = EnumSet.noneOf(UpdateType.class);
      if (Math.abs(model.getTime().getTime() - lastUpdateTimeMs) > UPDATE_FREQ_MS) {
        updateSpaceOjectObservation();
        updateTypes.add(UpdateType.Reset);
      }
      return updateTypes;
    }

    @Override
    public List<? extends ImageSource> getImages() {
      return imageSources;
    }

    @Override
    public List<? extends TextSource> getLabels() {
      return labelSources;
    }
  }
}
