public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
   
    mGray = inputFrame.gray();
    Mat resizeImg = new Mat();

    if (mPrevGray != null && !mPrevGray.empty()) {
       
        MatOfKeyPoint mKeyPoint = new MatOfKeyPoint();
        FeatureDetector.create(FeatureDetector.HARRIS).detect(mPrevGray, mKeyPoint);

        ArrayList<Point> temp = new ArrayList<Point>();
        List<KeyPoint> keyPointsList = mKeyPoint.toList();
        for (int i = 0; i<keyPointsList.size(); i++)
        {
            temp.add(keyPointsList.get(i).pt);
        }
        MatOfPoint2f needs = new MatOfPoint2f();
        needs.fromList(temp);
        MatOfPoint2f result = new MatOfPoint2f();
        MatOfByte status = new MatOfByte();
        MatOfFloat err = new MatOfFloat();

        calcOpticalFlowPyrLK(mPrevGray, mGray, needs, result, status, err);

    }

    mPrevGray = mGray;
    count++;

return mGray;
}
