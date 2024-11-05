import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const IconLabelCard = ({ label, icon, backgroundColor, textColor }) => (
  <CardContainer backgroundColor={backgroundColor}>
    <Label textColor={textColor}>{label}</Label>
    <IconContainer>{icon}</IconContainer>
  </CardContainer>
);

IconLabelCard.propTypes = {
  label: PropTypes.string.isRequired,
  icon: PropTypes.element.isRequired,
  backgroundColor: PropTypes.string,
  textColor: PropTypes.string,
};

IconLabelCard.defaultProps = {
  backgroundColor: '#a8c8f0',
  textColor: '#000',
};

export default IconLabelCard;

const CardContainer = styled.div`
  display: flex;
  align-items: center;
  padding: 20px;
  border-radius: 15px;
  background-color: ${(props) => props.backgroundColor};
  width: 270px;
  gap: 50px;
`;

const Label = styled.div`
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  padding: 5px 10px;
  color: ${(props) => props.textColor};
  font-weight: bold;
  margin-right: auto;
  max-width: 100px;
`;

const IconContainer = styled.div`
  font-size: 36px;
  margin-left: 20px;
  flex-shrink: 0;
`;
