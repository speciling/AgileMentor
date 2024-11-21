import React from 'react';
import styled from 'styled-components';
// eslint-disable-next-line import/no-unresolved
import IconLabelCard from '@components/common/IconLabelCard/index';
// eslint-disable-next-line import/no-unresolved
import GoogleLoginButton from '@components/common/LoginButton/index';
import { AiOutlineThunderbolt } from 'react-icons/ai';
import { CiFaceSmile } from 'react-icons/ci';
import { MdOutlineTrendingUp } from 'react-icons/md';
// eslint-disable-next-line import/no-unresolved
import { HEADER_HEIGHT } from '@components/features/Header';

const IntroducePage = () => (
  <PageContainer>
    <ContentContainer>
      <IntroText>
        <Title>
          애자일 방식으로 팀의 효율성과 협업을 극대화하는 애자일 학습 및
          프로젝트 관리 도구
        </Title>
        <Subtitle>
          Agile Mentor는 애자일 학습 및 프로젝트 관리 도구를 통해 팀이 애자일
          방법론을 효율적으로 적용하여 업무 효율성과 협업 능력을 극대화할 수
          있도록 지원합니다.
        </Subtitle>
        <GoogleLoginButton />
      </IntroText>
      <MainContent>
        <CardsContainer>
          <IconLabelCard
            label="Simple"
            icon={
              <AiOutlineThunderbolt
                style={{ fontSize: '36px', display: 'block', margin: '0 auto' }}
              />
            }
            backgroundColor="#9DC4F5"
          />
          <IconLabelCard
            label="Convenient"
            icon={
              <CiFaceSmile
                style={{ fontSize: '36px', display: 'block', margin: '0 auto' }}
              />
            }
            backgroundColor="#FBE74E"
          />
          <IconLabelCard
            label="Effective"
            icon={
              <MdOutlineTrendingUp
                style={{ fontSize: '36px', display: 'block', margin: '0 auto' }}
              />
            }
            backgroundColor="#FFCADC"
          />
        </CardsContainer>
        <ImagePlaceholder>이미지 추가 예정</ImagePlaceholder>
      </MainContent>
    </ContentContainer>
  </PageContainer>
);

export default IntroducePage;

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f9fafb;
  min-height: calc(100vh - ${HEADER_HEIGHT});
`;

const ContentContainer = styled.div`
  max-width: 1400px;
  width: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  flex: 1;
`;

const IntroText = styled.div`
  margin-top: 50px;
  width: 740px;
  text-align: center;
  margin-bottom: 30px;
`;

const Title = styled.h1`
  font-size: 40px;
  font-weight: bold;
  margin-bottom: 15px;
  color: #2c3e50;
`;

const Subtitle = styled.p`
  font-size: 19px;
  color: #7f8c8d;
  margin-bottom: 20px;
`;

const MainContent = styled.div`
  display: flex;
  width: 100%;
  gap: 40px;
  justify-content: space-between;
`;

const CardsContainer = styled.div`
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 50px;
  flex: 1;
  margin-right: 20px;
`;

const ImagePlaceholder = styled.div`
  flex: 2;
  max-width: 880px;
  width: 100%;
  height: 400px;
  background-color: #e0e0e0;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  color: #7f8c8d;
  font-size: 18px;
`;
